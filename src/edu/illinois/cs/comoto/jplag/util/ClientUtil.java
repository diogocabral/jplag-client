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

package edu.illinois.cs.comoto.jplag.util;

import com.sun.xml.rpc.client.ClientTransportException;
import com.sun.xml.rpc.util.exception.JAXRPCExceptionBase;
import edu.illinois.cs.comoto.jplag.wsdl.JPlagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Author:  Charlie Meyer <cemeyer2@illinois.edu>
 * Date:    1/26/11
 * Time:    4:50 PM
 * Package: edu.illinois.cs.comoto.jplag.util
 * Created by IntelliJ IDEA.
 */
public class ClientUtil {

    private ClientUtil(){}

    private static Properties props;

    public static String getProperty(String key) {
        if (props == null) {
            try {
                String propFileName = "jplag.properties";
                List<String> propertiesPaths = new LinkedList<String>();
                propertiesPaths.add(propFileName);
                propertiesPaths.add(System.getProperty("user.home") + File.separator + propFileName);
                propertiesPaths.add("/etc/jplag/"+propFileName);

                File propsFile = null;

                for (String path : propertiesPaths) {
                    File f = new File(path);
                    if (f.exists()) {
                        System.out.println("Using configuration from " + path);
                        propsFile = f;
                        break;
                    }
                }
                if (propsFile == null) {
                    System.out.println("Warning: could not find configuration file");
                    String paths = "";
                    for(String path : propertiesPaths) {
                        paths+=new File(path).getCanonicalPath()+":";
                    }
                    paths = paths.substring(0, paths.length()-1);
                    System.out.println("Searched paths: "+paths);
                    return "";
                }
                props = new Properties();
                props.load(new FileInputStream(propsFile));
            } catch (IOException e) {
                System.err.println("Error loading properties");
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
        String value = props.getProperty(key);
        if (value == null) {
            System.err.println("Warning: property: " + key + " not found in configuration file");
        }
        return value;
    }

    /**
     * Helper function to easily evaluate web service related exceptions
     *
     * @param e Exception thrown by a stub method
     */
    public static void checkException(Exception e) {
        if (e instanceof JPlagException) {
            JPlagException je = (JPlagException) e;
            System.err.println("JPlagException: " + je.getDescription()
                    + "\n" + je.getRepair());
        } else if (e instanceof RemoteException) {
            RemoteException re = (RemoteException) e;
            Throwable cause = re.getCause();
            if (cause != null && cause instanceof ClientTransportException) {
                cause = ((JAXRPCExceptionBase) cause).getLinkedException();
                if (cause != null) {
                    System.err.println("Connection exception: "
                            + cause.getMessage());
                    return;
                }
            }
            System.err.println("Unexpected RemoteException: "
                    + re.getMessage());
            re.printStackTrace();
        } else {
            System.err.println("Unexpected Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Concatenates the string representations of objects in an array
     *
     * @param array Object array
     * @return Comma-separated list of string representations of those objects
     */
    public static String arrayToString(Object[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += array[i].toString();
            if (i != array.length - 1) str += ",";
        }
        return str;
    }
}
