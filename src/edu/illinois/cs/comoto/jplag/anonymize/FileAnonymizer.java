/*
 * University of Illinois/NCSA
 * Open Source License
 *
 * Copyright (c) 2011 University of Illinois at Urbana-Champaign.
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
 *  Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimers.
 *
 *  Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimers in the documentation and/or other materials provided
 * with the distribution.
 *
 *  Neither the names of the CoMoTo Project team, the University of
 * Illinois at Urbana-Champaign, nor the names of its contributors
 * may be used to endorse or promote products derived from this
 * Software without specific prior written permission.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE SOFTWARE.
 */

package edu.illinois.cs.comoto.jplag.anonymize;

import edu.illinois.cs.comoto.jplag.LDAP.StudentData;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Author:  Charlie Meyer <cemeyer2@illinois.edu>
 * Date:    1/26/11
 * Time:    10:10 PM
 * Package: edu.illinois.cs.comoto.jplag.anonymize
 * Created by IntelliJ IDEA.
 */
public class FileAnonymizer {


    StudentData studentData;

    public FileAnonymizer(StudentData data) {
        this.studentData = data;
    }

    public Vector<File> anonymizeFiles(Vector<File> originalFiles) {

        System.out.println("Stripping all source files of student data");
        Vector<File> anonymizedFiles = new Vector<File>();

        String inputBaseDir = getBaseDir(originalFiles);

        File outputDir = createTempDir();

        for (File inputFile : originalFiles) {
            try {
                File outputFile = new File(outputDir.getCanonicalPath() + File.separator + getAnonymizedSubPath(inputFile, inputBaseDir));
                anonymizeFile(inputFile, outputFile);
                anonymizedFiles.add(outputFile);
            } catch (IOException ioe) {
                System.err.println("Error anonymizing files");
                System.err.println(ioe.getMessage());
                ioe.printStackTrace();
                System.exit(1);
            }
        }

        return anonymizedFiles;
    }

    private String getAnonymizedSubPath(File inputFile, String baseDir) {
        try {
            String inputPath = inputFile.getCanonicalPath();
            String retval = inputPath.substring(baseDir.length());
            int marker = retval.indexOf(File.separator);
            int marker2 = retval.indexOf('_');
            if (marker2 != -1 && marker2 < marker) {
                marker = marker2;
            }
            String netid = retval.substring(0, marker);
            String pseudonym = studentData.getNetids().get(netid);
            if (pseudonym != null) {
                retval = retval.replace(netid, pseudonym);
            }
            return retval;
        } catch (IOException ioe) {
            System.err.println("Error anonymizing path");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        }
        return null; //shouldnt get here
    }

    /**
     * @param files a vector of file objects
     * @return a string representing the greatest common base directory of all files in the input vector
     */
    private String getBaseDir(Vector<File> files) {
        String baseDir = null;
        try {
            baseDir = files.get(0).getCanonicalPath();
            for (File f : files) {
                baseDir = getLongestCommonPath(f, baseDir);
            }

        } catch (IOException ioe) {
            System.err.println("Error anonymizing files");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        }
        return baseDir;
    }

    private String getLongestCommonPath(File f, String current) throws IOException {
        String output = "";
        String fPath = f.getCanonicalPath();

        int iterMax = current.length();
        if (fPath.length() < iterMax) {
            iterMax = fPath.length();
        }

        for (int i = 0; i < iterMax; i++) {
            if (current.charAt(i) == fPath.charAt(i)) {
                output += current.charAt(i); //could be fPath.charAt(i); too
            } else {
                break;
            }
        }
        return output;
    }

    private void anonymizeFileNaive(File inputFile, File outputFile) {
        try {
            if (!outputFile.exists()) {
                File outputDir = new File(outputFile.getCanonicalPath().substring(0, outputFile.getCanonicalPath().lastIndexOf(File.separator)));
                outputDir.mkdirs();
                outputFile.createNewFile();
            }
            String text = FileUtils.readFileToString(inputFile);
            for (String netid : studentData.getNetids().keySet()) {
                text.replaceAll(regexSanitizeString(netid), studentData.getNetids().get(netid));
            }
            for (String firstName : studentData.getFirstNames()) {
                firstName = regexSanitizeString(firstName);
                text.replaceAll(" " + firstName + " ", " FERPA_santized_first_name ");
            }
            for (String lastName : studentData.getLastNames()) {
                lastName = regexSanitizeString(lastName);
                text.replaceAll(" " + lastName + " ", " FERPA_sanitized_last_name ");
            }
            FileUtils.writeStringToFile(outputFile, text);
        } catch (IOException ioe) {
            System.err.println("Error anonymizing files");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        }

    }

    private void anonymizeFile(File inputFile, File outputFile) {
        try {
            if (!outputFile.exists()) {
                File outputDir = new File(outputFile.getCanonicalPath().substring(0, outputFile.getCanonicalPath().lastIndexOf(File.separator)));
                outputDir.mkdirs();
                outputFile.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            PrintWriter out = new PrintWriter(new FileWriter(outputFile));
            String line = "";
            while ((line = in.readLine()) != null) {
                String[] split = line.split(" ");
                for (String word : split) {
                    if (studentData.getNetids().containsKey(word)) {
                        line = line.replace(" " + word + " ", " " + studentData.getNetids().get(word) + " ");
                    }
//                    else if(studentData.getFirstNames().contains(word)) {
//                        line = line.replace(word, "FERPA_sanitized_first_name");
//                    } else if(studentData.getLastNames().contains(word)) {
//                        line = line.replace(word, "FERPA_sanitized_last_name");
//                    }
                }
                out.println(line);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException ioe) {
            System.err.println("Error anonymizing files");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    //modified from http://stackoverflow.com/questions/375910/creating-a-temp-dir-in-java
    private File createTempDir() {
        final String baseTempPath = System.getProperty("java.io.tmpdir");

        Random rand = new Random();
        int randomInt = 1 + rand.nextInt();

        File tempDir = new File(baseTempPath + File.separator + "CoMoToJPlagClientTempDir" + randomInt);
        if (tempDir.exists() == false) {
            tempDir.mkdir();
        }

        tempDir.deleteOnExit();

//        try {
//            System.out.println("temp dir: " + tempDir.getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        return tempDir;
    }

    private String regexSanitizeString(String input) {
        input = input.replace("(", "\\(");
        input = input.replace(")", "\\)");
        return input;
    }

}
