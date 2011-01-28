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

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:  Charlie Meyer <cemeyer2@illinois.edu>
 * Date:    1/27/11
 * Time:    10:48 AM
 * Package: edu.illinois.cs.comoto.jplag.anonymize
 * Created by IntelliJ IDEA.
 */
public class FileDeAnonymizer {

    private StudentData studentData;

    public FileDeAnonymizer(StudentData studentData) {
        this.studentData = studentData;
    }

    public void deanonymizeFiles(File dir) {
        List<File> files = new LinkedList<File>();
        collectFiles(files, dir);
        for (File f : files) {
            deanonymizeFile(f);
        }
    }

    private void collectFiles(List<File> files, File dir) {
        File[] ls = dir.listFiles();
        for (File f : ls) {
            if (f.isDirectory()) {
                collectFiles(files, f);
            } else {
                files.add(f);
            }
        }
    }

    private void deanonymizeFile(File f) {
        try {
            String contents = FileUtils.readFileToString(f);

            String regex = "CoMoTo_FERPA_student\\d{10}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(contents);
            while (matcher.find()) {
                String pseudonym = matcher.group();
                contents = contents.replace(pseudonym, studentData.getNetids_reverse().get(pseudonym));
            }

            f.delete(); //the following write only succeeds if the file to write to does not exist
            FileUtils.writeStringToFile(f, contents);
        } catch (IOException ioe) {
            System.err.println("Error deanonymizing file: " + f.getName());
            System.err.println(ioe.getMessage());
            System.exit(1);
        }
    }
}
