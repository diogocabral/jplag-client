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

package edu.illinois.cs.comoto.jplag;

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.Option;
import jargs.gnu.CmdLineParser.OptionException;

/**
 * Created by IntelliJ IDEA.
 * User: chuck
 * Date: 1/26/11
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class JPlagClient {

    public JPlagClient(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {

        CmdLineParser parser = new CmdLineParser();

        Option userOption = parser.addStringOption('u', "user");
        Option passOption = parser.addStringOption('p', "pass");
        Option languageOption = parser.addStringOption('l', "language");
        Option sourceOption = parser.addStringOption('s', "source");
        Option subdirsOption = parser.addBooleanOption("subdirs");
        Option extensionsOption = parser.addStringOption('e', "extensions");
        Option tokensOption = parser.addIntegerOption('t', "tokens");
        Option matchesNumberOption = parser.addIntegerOption('m', "matches");
        Option matchesPctOption = parser.addIntegerOption('%', "pct");
        Option baseOption = parser.addStringOption('b', "base");
        Option destOption = parser.addStringOption('d', "destination");
        Option titleOption = parser.addStringOption("title");
        Option localeOption = parser.addStringOption("locale");
        Option helpOption1 = parser.addBooleanOption('h', "help");
        Option helpOption2 = parser.addBooleanOption('?', "?");
        Option anonymizeOption = parser.addBooleanOption('a', "anonymize");
        Option clusterOption = parser.addStringOption('c', "cluster");

        try {
            parser.parse(args);
        } catch (OptionException e) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
    }

    private void printUsage() {
        System.err.println("UIUC JPlag Client");
        System.err.println("Developed for the CoMoTo project");
        System.err.println("");
        System.err.println("Copyright (c) 2011 University of Illinois at Urbana-Champaign. All rights reserved.");
        System.err.println("");
        System.err.println("");
        System.err.println("Valid Options:");
        System.err.println("");
        optionUsage("-u <username>  --user <username>", "Sets the username (required).");
        optionUsage("-p <password>  --pass <password>", "Sets the password (required).");
        optionUsage("-l <language>  --language <language>", "Sets the programming language. Use -l ? or --language ? for supported and default languages.");
        optionUsage("-s <dir>  --source <dir>", "Sets the directory where source code is located. Default is cwd.");
        optionUsage("--subdirs", "Recursively scan subdirectories as well for source code files. Default is disabled.");
        optionUsage("-e <extensions>  --extensions <extensions>", "A comma separated list of file name extensions to include. Default is language specific.");
        optionUsage("-t <n>  --tokens <n>", "Sets the minimum match length in tokens. A smaller value increases the sensitivity of the comparison.");
        optionUsage("-m <n>  --matches <n>", "Sets the number of matches to return.");
        optionUsage("-% <n>  --pct <n>", "Sets the minimum percent for a match to be returned.");
        optionUsage("-b <dir>  --base <dir>", "Sets the directory for base code (typically instructor supplied files)");
        optionUsage("-d <dir>  --destination <dir>", "Sets the destination for output. Default cwd/results/");
        optionUsage("--title <title>", "Sets the title for the outputted report.");
        optionUsage("--locale <language>", "Sets the language the the output will be written in. Use --locale ? for a list of supported languages and default.");
        optionUsage("-a  --anonymize", "Strips all netids out of source code files. This can be a lengthy process. Default: disabled.");
        optionUsage("-c <type>  --cluster <type>", "Sets the cluster type. May be one of none, min, avr, or max. Default: none.");
        optionUsage("-h  -?  --help  --?", "This text.");

    }

    private void optionUsage(String option, String helpText) {
        System.err.println(option);
        System.err.println("\t" + helpText);
    }

    public static void main(String[] args) {
        new JPlagClient(args);
    }
}
