/*
 * University of Illinois/NCSA
 * Open Source License
 *
 * Copyright (c) 2012 University of Illinois at Urbana-Champaign.
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal with the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimers.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimers in the documentation and/or other materials provided
 *       with the distribution.
 *
 *     * Neither the names of the CoMoTo Project team, the University of
 *       Illinois at Urbana-Champaign, nor the names of its contributors
 *       may be used to endorse or promote products derived from this
 *       Software without specific prior written permission.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE SOFTWARE.
 */

package edu.illinois.cs.comoto.jplag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerChain;

import edu.illinois.cs.comoto.jplag.util.ClientUtil;
import edu.illinois.cs.comoto.jplag.util.CmdLineParser;
import edu.illinois.cs.comoto.jplag.util.CoMoToOption;
import edu.illinois.cs.comoto.jplag.util.JPlagClientAccessHandler;
import edu.illinois.cs.comoto.jplag.util.NonRecursiveFilenameFilter;
import edu.illinois.cs.comoto.jplag.util.NullStringAllowingStringOption;
import edu.illinois.cs.comoto.jplag.util.RecursiveFilenameFilter;
import edu.illinois.cs.comoto.jplag.util.ZipUtil;
import edu.illinois.cs.comoto.jplag.wsdl.JPlagService_Impl;
import edu.illinois.cs.comoto.jplag.wsdl.JPlagTyp_Stub;
import edu.illinois.cs.comoto.jplag.wsdl.LanguageInfo;
import edu.illinois.cs.comoto.jplag.wsdl.ServerInfo;
import edu.illinois.cs.comoto.jplag.wsdl.Status;

/**
 * Created by IntelliJ IDEA.
 * User: chuck
 * Date: 1/26/11
 * Time: 2:05 PM
 * <p/>
 * This code heavily adopted from example jplag client code
 */
public class JPlagClient {

    /*
    * Status constants
    */
    public static final int JPLAG_UPLOADING = 0;
    public static final int JPLAG_INQUEUE = 50;
    public static final int JPLAG_PARSING = 100;
    public static final int JPLAG_COMPARING = 200;
    public static final int JPLAG_GENRESULT = 230;
    public static final int JPLAG_PACKRESULT = 250;
    public static final int JPLAG_DONE = 300;
    public static final int JPLAG_ERROR = 400;


    private CoMoToOption option;
    private JPlagTyp_Stub stub = null;
    private FilenameFilter subdirFileFilter = null;
//    private StudentData studentData = null;

    public JPlagClient(String[] args) {
        run(args);
    }

    private void run(String[] args) {
        parseArgs(args);
        boolean initd = initJPlagStub();
        ServerInfo info;
        try {
            info = stub.getServerInfo();
            if (!checkOptions(info)) {
                System.exit(1);
            }
        } catch (Exception e) {
            ClientUtil.checkException(e);
            System.exit(1);
        }


        System.out.println("Sending files to JPlag");
        String submissionID = sendSubmission();
        if (submissionID == null) return;

        System.out.println("Waiting for result");
        if (!waitForResult(submissionID)) {
            return;
        }

        System.out.println("Downloading result");
        if (!receiveResult(submissionID)) {
            return;
        }

        System.out.println("The result downloaded to output directory");

    }

    private void parseArgs(String[] args) {

        if (args.length == 0) {
            printUsage();
            System.exit(0);
        }

        CmdLineParser parser = new CmdLineParser();

        CmdLineParser.Option userOption = parser.addStringOption('u', "user");
        CmdLineParser.Option passOption = parser.addStringOption('p', "pass");
//        CmdLineParser.Option languageOption = parser.addStringOption('l', "language");
        CmdLineParser.Option languageOption = parser.addOption(new NullStringAllowingStringOption('l', "language"));
        CmdLineParser.Option sourceOption = parser.addStringOption('s', "source");
        CmdLineParser.Option subdirsOption = parser.addBooleanOption("subdirs");
        CmdLineParser.Option extensionsOption = parser.addStringOption('e', "extensions");
        CmdLineParser.Option tokensOption = parser.addIntegerOption('t', "tokens");
        CmdLineParser.Option matchesOption = parser.addStringOption('m', "matches");
        CmdLineParser.Option baseOption = parser.addStringOption('b', "base");
        CmdLineParser.Option destOption = parser.addStringOption('d', "destination");
        CmdLineParser.Option titleOption = parser.addStringOption("title");
//        CmdLineParser.Option localeOption = parser.addStringOption("locale");
        CmdLineParser.Option localeOption = parser.addOption(new NullStringAllowingStringOption("locale"));
        CmdLineParser.Option helpOption1 = parser.addBooleanOption('h', "help");
        CmdLineParser.Option helpOption2 = parser.addBooleanOption('?', "?");
        CmdLineParser.Option anonymizeOption = parser.addBooleanOption('a', "anonymize");
        CmdLineParser.Option clusterOption = parser.addStringOption('c', "cluster");

        try {
            parser.parse(args);
        } catch (CmdLineParser.OptionException e) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }

        //first check for help
        boolean help1 = ((Boolean) parser.getOptionValue(helpOption1, Boolean.FALSE)).booleanValue();
        boolean help2 = ((Boolean) parser.getOptionValue(helpOption2, Boolean.FALSE)).booleanValue();

        if (help1 || help2) {
            printUsage();
            System.exit(0);
        }

        //now get the rest of the arguments
        String username = (String) parser.getOptionValue(userOption, ClientUtil.getProperty("jplag.username"));
        String password = (String) parser.getOptionValue(passOption, ClientUtil.getProperty("jplag.password"));
        String language = (String) parser.getOptionValue(languageOption, "text");
        String sourceDir = verifyFilePath((String) parser.getOptionValue(sourceOption, "."), false);
        boolean subdirs = ((Boolean) parser.getOptionValue(subdirsOption, Boolean.FALSE)).booleanValue();
        String[] extensions = ((String) parser.getOptionValue(extensionsOption, "")).split(",");
        if (extensions[0].equals("")) {
            extensions = null;
        }
        int tokens = ((Integer) parser.getOptionValue(tokensOption, Integer.valueOf(-1))).intValue();
        String matches = (String) parser.getOptionValue(matchesOption, "20");
        String baseDir = (String) parser.getOptionValue(baseOption, "");
        if (baseDir.equals("")) {
            baseDir = null;
        }
        String destDefault = ".";
        try {
            destDefault = new File(".").getCanonicalPath() + File.separator + "result" + File.separator;
        } catch (IOException ioe) {
            //ignore silently
        }
        String destDir = verifyFilePath((String) parser.getOptionValue(destOption, destDefault), true);
        String title = (String) parser.getOptionValue(titleOption, "CoMoTo JPlag Report");
        String locale = (String) parser.getOptionValue(localeOption, "en");
        boolean anonymize = ((Boolean) parser.getOptionValue(anonymizeOption, Boolean.FALSE)).booleanValue();
        String cluster = (String) parser.getOptionValue(clusterOption, "none");

        if (!sourceDir.startsWith(File.separator)) {
            try {
                sourceDir = new File(".").getCanonicalPath() + File.separator + sourceDir;
            } catch (IOException e) {
                ClientUtil.checkException(e);
                System.exit(1);
            }
        }
        option = new CoMoToOption();
        option.setUsername(username);
        option.setPassword(password);
        option.setLanguage(language);
        option.setSourceDir(sourceDir);
        option.setSubdirs(subdirs);
        option.setExtensions(extensions);
        option.setTokens(tokens);
        option.setMatches(matches);
        option.setBaseDir(baseDir);
        option.setDestDir(destDir);
        option.setTitle(title);
        option.setLocale(locale);
        option.setAnonymize(anonymize);
        option.setCluster(cluster);
    }

    private String verifyFilePath(String path, boolean create) {
        File f = new File(path);
        if (!f.exists() && create) {
            boolean success = f.mkdir();
            if (!success) {
                System.err.println("Error: Could not create directory: " + path);
                System.exit(1);
            }
        }
        if (!f.exists()) {
            System.err.println("Supplied path " + path + " does not exist!");
            System.exit(1);
        }
        if (!f.isDirectory()) {
            System.err.println("Supplied path " + path + " is not a directory!");
            System.exit(1);
        }
        return path;
    }

    private void printUsage() {
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        System.err.println("UIUC JPlag Client");
        System.err.println("Developed for the CoMoTo project by Charlie Meyer <cemeyer2@illinois.edu>");
        System.err.println("");
        System.err.println("Copyright (c) 2011-" + thisYear + " University of Illinois at Urbana-Champaign. All rights reserved.");
        System.err.println("");
        System.err.println("");
        System.err.println("Valid Options:");
        System.err.println("");
        optionUsage("-u <username>  --user <username>", "Sets the username. Default: from configuration file");
        optionUsage("-p <password>  --pass <password>", "Sets the password. Default: from configuration file");
        optionUsage("-l <language>  --language <language>", "Sets the programming language.\nUse -l ? or --language ? for supported and default languages.");
        optionUsage("-s <dir>  --source <dir>", "Sets the directory where source code is located. Default is cwd.");
        optionUsage("--subdirs", "Recursively scan subdirectories as well for source code files. Default is disabled.");
        optionUsage("-e <extensions>  --extensions <extensions>", "A comma separated list of file name extensions to include.\nDefault is language specific.");
        optionUsage("-t <n>  --tokens <n>", "Sets the minimum match length in tokens.\nA smaller value increases the sensitivity of the comparison.");
        optionUsage("-m <matches>  --matches <matches>", "Sets the number of matches to return. If a number is specified, that many\nmatches will be returned. If a number followed by % is specified, matches greater than that similarity % will be returned. Default: 20");
        optionUsage("-b <dir>  --base <dir>", "Sets the directory for base code (typically instructor supplied files)");
        optionUsage("-d <dir>  --destination <dir>", "Sets the destination for output. Default cwd/results/");
        optionUsage("--title <title>", "Sets the title for the outputted report.");
        optionUsage("--locale <language>", "Sets the language the the output will be written in.\nUse --locale ? for a list of supported languages and default.");
        optionUsage("-a  --anonymize", "Strips all netids out of source code files. This can be a lengthy process. Default: disabled.");
        optionUsage("-c <type>  --cluster <type>", "Sets the cluster type. May be one of none, min, avr, or max. Default: none.");
        optionUsage("-h  -?  --help  --?", "This text.");

    }

    private void optionUsage(String option, String helpText) {
        System.err.println("\t" + option);
        System.err.println("\t\t" + helpText.replaceAll("\n", "\n\t\t"));
    }

    private void printLanguageHelp(LanguageInfo[] languages) {
        if (!option.getLanguage().equals("?")) {
            System.out.println("Unknown language: \""
                    + option.getLanguage() + "\"");
        }
        System.out.println("\nAvailable languages:");
        for (int i = 0; i < languages.length; i++) {
            System.out.println(" - \"" + languages[i].getName()
                    + "\"" + (i == 0 ? " (default language)\n" : "\n")
                    + "   default minimum match length = "
                    + languages[i].getDefMinMatchLen()
                    + "\n   default suffixes: "
                    + ClientUtil.arrayToString(languages[i].getSuffixes()));
        }
        System.exit(0);
    }

    private void printLocaleHelp(String[] locales) {
        if (!option.getLocale().equals("?")) {
            System.out.println("Unknown country language: \""
                    + option.getLocale() + "\"");
        }
        System.out.println("\nAvailable country languages:");
        for (int i = 0; i < locales.length; i++) {
            System.out.println(" - \"" + locales[i]
                    + (i == 0 ? "\" (default)" : "\""));
        }
        System.exit(0);
    }

    /**
     * Checks the current options for validity using the information provided
     * by the ServerInfo object and fills remaining empty fields with defaults.
     * If "-l ?" or "-cl ?" is used, a list of valid languages respectively
     * country languages is printed and false is returned.
     * <p/>
     * modified from original jplag client sample code
     *
     * @param info ServerInfo object
     * @return True, if all options are legal
     */
    private boolean checkOptions(ServerInfo info) {
        LanguageInfo[] languages = info.getLanguageInfos();
        String[] countryLangs = info.getCountryLanguages();
        int i;
        if (option.getLanguage() == null) {
            i = 0;
            option.setLanguage(languages[0].getName());
            System.err.println("Using default language: "
                    + languages[0].getName());
        } else {
            for (i = 0; i < languages.length; i++) {
                if (option.getLanguage().equals(languages[i].getName())) break;
            }
            if (i == languages.length) {
                printLanguageHelp(languages);
            }
        }
        if (option.getExtensions() == null) {
            option.setExtensions(languages[i].getSuffixes());
            System.out.println("Using default suffixes: "
                    + ClientUtil.arrayToString(option.getExtensions()));
        }

        if (option.getTitle() == null) {
            option.setTitle("CoMoTo JPlag submission-"
                    + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        if (option.getLocale() == null) {
            option.setLocale("en");
        } else {
            for (i = 0; i < countryLangs.length; i++) {
                if (option.getLocale().equals(countryLangs[i])) break;
            }
            if (i == countryLangs.length) {
                printLocaleHelp(countryLangs);
            }
        }
        return true;
    }


    /**
     * Initializes the JPlag stub, by installing an all-trusting trust manager
     * for the SSL connection to the server, instantiating a stub object and
     * setting username and password
     * <p/>
     * This method adopted from example jplag client code
     *
     * @return True, if username and password have been set
     */
    private boolean initJPlagStub() {
        /*
         * Create a trust manager that does not validate certificate chains
         */
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs,
                                                   String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs,
                                                   String authType) {
                    }
                }
        };

        /*
         * Install the all-trusting trust manager
         */
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Warning: Unable to install all-trusting trust "
                    + "manager! SSL connection may not work!");
        }

        /*
         * Get JPlag client stub
         */
        stub = (JPlagTyp_Stub) (new JPlagService_Impl()
                .getJPlagServicePort());

        /*
         * Search for the JPlagClientAccessHandler in the handler chain
         */
        HandlerChain handlerchain = stub._getHandlerChain();
        
        @SuppressWarnings("unchecked")
		Iterator<Handler> handlers = handlerchain.iterator();
        
        JPlagClientAccessHandler accessHandler = null;
        while (handlers.hasNext()) {
            Handler handler = handlers.next();
            if (handler instanceof JPlagClientAccessHandler) {
                accessHandler = (JPlagClientAccessHandler) handler;
                break;
            }
        }

        if (accessHandler == null) {
            System.err.println("Unable to find access handler! Cannot set "
                    + "username and password!");
            System.exit(1);
        }

        /*
         * Initialize access handler
         */
        accessHandler.setUserPassObjects(option.getUsername(), option.getPassword());

        return true;
    }

    private String sendSubmission() {
        Vector<File> submissionFiles = collectFiles();

//        if (option.isAnonymize()) {
//            studentData = new UIUCLDAPDownloader().getStudentData();
//            submissionFiles = new FileAnonymizer(studentData).anonymizeFiles(submissionFiles);
//        }

        if (submissionFiles == null) return null;

        File zipfile = null;
        String submissionID = null;

        try {
            zipfile = File.createTempFile("jplagtmp", ".zip");
            ZipUtil.zipFilesTo(submissionFiles, option.toOption().getOriginalDir(),
                    zipfile);

            FileDataSource fds = new FileDataSource(zipfile);
            MimeMultipart mmp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setDataHandler(new DataHandler(fds));
            mbp.setFileName(zipfile.getName());
            mmp.addBodyPart(mbp);

            submissionID = stub.compareSource(option.toOption(), mmp);
            zipfile.delete();
        } catch (Exception e) {
            System.out.println();
            ClientUtil.checkException(e);
            if (zipfile != null) zipfile.delete();
            System.exit(1);
        }
        return submissionID;
    }

    private Vector<File> collectFiles() {
        Vector<File> colfiles = new Vector<File>();
        File[] files = new File(option.toOption().getOriginalDir()).listFiles(
                new RecursiveFilenameFilter(option));

        if (files == null) {
            System.out.println("\"" + option.toOption().getOriginalDir()
                    + "\" is not a directory or an I/O error occurred!");
            System.exit(1);
        }

        if (option.toOption().isReadSubdirs()) {
            subdirFileFilter = new RecursiveFilenameFilter(option);
        } else {
            subdirFileFilter = new NonRecursiveFilenameFilter(option);
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                if (option.toOption().getPathToFiles() != null) {
                    collectInDir(colfiles, new File(files[i],
                            option.toOption().getPathToFiles()));
                } else {
                    collectInDir(colfiles, files[i]);
                }
            } else {
                colfiles.add(files[i]);
            }
        }

        if (colfiles.size() <= 1) {
            System.out.println("\"" + option.toOption().getOriginalDir()
                    + "\" didn't contain at least two files\n"
                    + "suitable for the specified options!");
            return null;
        }
        return colfiles;
    }

    private void collectInDir(Vector<File> colfiles, File dir) {
        if (!dir.exists()) return;

        File[] files = dir.listFiles(subdirFileFilter);

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                collectInDir(colfiles, files[i]);
            } else {
                colfiles.add(files[i]);
            }
        }
    }

    private boolean waitForResult(String submissionID) {
        Status status;
        try {
            while (true) {
                status = stub.getStatus(submissionID);

                if (status.getState() == JPLAG_INQUEUE) {
                    System.out.println("Submission in queue");
                } else if (status.getState() == JPLAG_PARSING) {
                    System.out.println("Parsing files");
                } else if (status.getState() == JPLAG_COMPARING) {
                    System.out.println("Comparing files");
                } else if (status.getState() == JPLAG_GENRESULT) {
                    System.out.println("Generating result");
                } else if (status.getState() == JPLAG_PACKRESULT) {
                    System.out.println("Packing result");
                }

                if (status.getState() >= JPLAG_DONE) {
                    break;
                }
                Thread.sleep(3000);
            }
            if (status.getState() >= JPLAG_ERROR) {
                /*
                 * An error occurred: Print out error message and acknowledge
                 * error by cancelling the submission
                 */
                System.out.println("\nSome error occurred: "
                        + status.getReport());
                stub.cancelSubmission(submissionID);
                return false;
            }
        } catch (Exception e) {
            ClientUtil.checkException(e);
            System.exit(1);
        }
        return true;
    }

    private boolean receiveResult(String submissionID) {
        File zipfile = null;
        FileOutputStream output = null;

        try {
            File resultDir = new File(option.getDestDir());
            if (!resultDir.exists()) resultDir.mkdirs();
            zipfile = File.createTempFile("jplagtmpresult", ".zip");

            MimeMultipart resultMultipart = stub.getResult(submissionID);

            MimeBodyPart bdp = (MimeBodyPart) resultMultipart.getBodyPart(0);
            DataHandler dh = bdp.getDataHandler();
            output = new FileOutputStream(zipfile);
            dh.writeTo(output);
            output.close();

            /*
             * Unzip result archive and delete the zip file
             */

            ZipUtil.unzip(zipfile, resultDir);
            zipfile.delete();

            /*
             * Result successfully downloaded and unzipped,
             * so remove it from server now
             */
            stub.cancelSubmission(submissionID);
//            if (option.isAnonymize()) {
//                new FileDeAnonymizer(studentData).deanonymizeFiles(resultDir);
//            }
        } catch (Exception e) {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ex) {
                }
            }
            if (zipfile != null) zipfile.delete();
            ClientUtil.checkException(e);
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        new JPlagClient(args);
    }
}
