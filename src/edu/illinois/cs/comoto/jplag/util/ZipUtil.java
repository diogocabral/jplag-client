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

/*
 * Created on 10.02.2005
 */
package edu.illinois.cs.comoto.jplag.util;

import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private ZipUtil(){}


    /**
     * Zips the given directory "dir" into a zip file in "dest"
     *
     * @param dir  File denoting the directory to be zipped
     * @param dest Name of the directory where the zipped file will be stored,
     *             which will be named dir.getName()+".zip"
     * @return zipped file
     */
    public static File zip(File dir, String dest) {
        File zippedFile = new File(dest + "/" + dir.getName() + ".zip");
        zipTo(dir, zippedFile);
        return zippedFile;
    }

    /**
     * Zips the given directory "dir" into the zip file "destFile".
     * If "destFile" already exists, it will be overwritten
     *
     * @param dir      Directory to be zipped
     * @param destFile Destination file
     */
    public static void zipTo(File dir, File destFile) {
        FileOutputStream ops = null;
        ZipOutputStream zos = null;
        try {
            ops = new FileOutputStream(destFile);
            zos = new ZipOutputStream(ops);
            zipDir(dir, zos, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null)
                    zos.close();
                else if (ops != null)
                    ops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Recursively zips all files in "dir" and its subdirectories into the given
     * ZipOutputStream "zos" using the given path prefix for their names
     */
    private static void zipDir(File dir, ZipOutputStream zos, String prefix) {
        File[] entries = dir.listFiles();
        for (int i = 0; i < entries.length; i++) {
            if (entries[i].isDirectory()) {
                // generate directory entry
                ZipEntry zi = new ZipEntry(prefix + "/" + entries[i].getName()
                        + "/");
                try {
                    zos.putNextEntry(zi);
                    zos.closeEntry();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
                zipDir(entries[i], zos, prefix + "/" + entries[i].getName());
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(entries[i]);
                    ZipEntry zi = new ZipEntry(prefix + "/"
                            + entries[i].getName());
                    zos.putNextEntry(zi);
                    copystream(fis, zos);
                    zos.closeEntry();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                } finally {
                    try {
                        if (fis != null)
                            fis.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * Zips all files in "fileVector" to the zipfile "destFile".
     * The pathnames of all files in fileVector must start with baseDir!
     *
     * @param fileVector Files to be zipped
     * @param baseDir    Root directory for this zip file
     * @param destFile   Destination file
     */
    public static void zipFilesTo(Vector<File> fileVector, String baseDir,
                                  File destFile) {
        FileOutputStream ops = null;
        ZipOutputStream zos = null;
        int basedirlen = baseDir.length();
        if (!baseDir.endsWith(File.separator)) basedirlen++;
        try {
            ops = new FileOutputStream(destFile);
            zos = new ZipOutputStream(ops);

            Iterator<File> iter = fileVector.iterator();
            while (iter.hasNext()) {
                File file = iter.next();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    String name = file.getPath().substring(basedirlen);
                    name = name.replace('\\', '/'); // Zip uses '/' as separator
                    ZipEntry zi = new ZipEntry(name);
                    zos.putNextEntry(zi);
                    copystream(fis, zos);
                    zos.closeEntry();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fis != null)
                            fis.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null)
                    zos.close();
                else if (ops != null)
                    ops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Unzips the zip file "file" into the directory "dest"
     *
     * @param file    The zip file
     * @param destDir Directory where the content of the zip file will be saved
     */
    public static void unzip(File file, File destDir) {
        destDir.mkdir();
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry ze = (ZipEntry) entries.nextElement();
                if (ze.isDirectory())
                    (new File(destDir, ze.getName())).mkdir();
                else {
                    // make sure directories exist in case the client
                    // didn't provide directory entries!

                    File f = new File(destDir, ze.getName());
                    (new File(f.getParent())).mkdirs();

                    FileOutputStream fos = null;
                    BufferedOutputStream bos = null;
                    InputStream in = null;
                    try {
                        fos = new FileOutputStream(f);
                        bos = new BufferedOutputStream(fos);
                        in = zipFile.getInputStream(ze);
                        copystream(in, bos);
                    } finally {
                        if (bos != null)
                            bos.close();
                        else if (fos != null)
                            fos.close();
                        if (in != null)
                            in.close();
                    }
                }
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    /**
     * Copies the input stream to the output stream using a 1 kB buffer
     *
     * @throws IOException
     */
    private static void copystream(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
    }

    public static void main(String[] args) {
        // zip(new File("/home/bikiri/Desktop/jplag-old"),
        // "/home/bikiri/Desktop");
        unzip(new File("/home/bikiri/Desktop/emma.zip"),
                new File("/home/bikiri/Desktop", "unzipresult"));
    }
}
