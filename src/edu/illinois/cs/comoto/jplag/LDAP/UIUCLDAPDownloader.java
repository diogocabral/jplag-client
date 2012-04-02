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

package edu.illinois.cs.comoto.jplag.LDAP;

import edu.illinois.cs.comoto.jplag.util.ClientUtil;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Author:  Charlie Meyer <cemeyer2@illinois.edu>
 * Date:    1/26/11
 * Time:    5:12 PM
 * Package: edu.illinois.cs.comoto.jplag.LDAP
 * Created by IntelliJ IDEA.
 */
public class UIUCLDAPDownloader {

    public UIUCLDAPDownloader() {

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new UIUCLDAPDownloader().getStudentData();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //code adopted from http://download.oracle.com/javase/tutorial/jndi/newstuff/examples/PagedSearch.java
    public StudentData getStudentData() {
        System.out.println("Fetching student data from UIUC directory.");
        HashMap<String, String> netids = new HashMap<String, String>();
        HashMap<String, String> netids_reverse = new HashMap<String, String>();
        HashSet<String> firstNames = new HashSet<String>();
        HashSet<String> lastNames = new HashSet<String>();

        InitialLdapContext ctx = getDirContext();
        SearchControls ctls = getSearchControls();

        int pageSize = 500;
        byte[] cookie = null;
        try {
            ctx.setRequestControls(new Control[]{new PagedResultsControl(pageSize, Control.CRITICAL)});
        } catch (Exception e) {
            System.err.println("Error setting LDAP page controls");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            int count = Integer.MAX_VALUE - 1;
            do {
                NamingEnumeration answer = ctx.search("OU=Campus Accounts,DC=ad,DC=uiuc,DC=edu", "cn=*", ctls);

                while (answer != null && answer.hasMore()) {
                    SearchResult entry = (SearchResult) answer.next();
                    Attributes attrs = entry.getAttributes();
                    try {
                        String netid = (String) attrs.get("sAMAccountName").get();
                        String pseudonym = "CoMoTo_FERPA_student" + count;
                        netids.put(netid, pseudonym);
                        netids_reverse.put(pseudonym, netid);
                    } catch (Exception e) {
                    }
                    try {
                        String firstName = (String) attrs.get("givenName").get();
                        firstNames.add(firstName);
                    } catch (Exception e) {
                    }
                    try {
                        String lastName = (String) attrs.get("sn").get();
                        lastNames.add(lastName);
                    } catch (Exception e) {
                    }
                    count--;
                }
                Control[] controls = ctx.getResponseControls();
                if (controls != null) {
                    for (int i = 0; i < controls.length; i++) {
                        if (controls[i] instanceof PagedResultsResponseControl) {
                            PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
                            cookie = prrc.getCookie();
                        }
                    }
                } else {
                    System.out.println("No controls were sent from the server");
                }
                // Re-activate paged results
                ctx.setRequestControls(new Control[]{
                        new PagedResultsControl(pageSize, cookie, Control.CRITICAL)});

            } while (cookie != null);

            ctx.close();

        } catch (NamingException e) {
            System.err.println("LDAP Error performing LDAP search");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Error performing LDAP search");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        StudentData data = new StudentData();
        data.setFirstNames(firstNames);
        data.setLastNames(lastNames);
        data.setNetids(netids);
        data.setNetids_reverse(netids_reverse);
        return data;
    }

    private InitialLdapContext getDirContext() {
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://ad.uiuc.edu:636");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ClientUtil.getProperty("ldap.username"));
            env.put(Context.SECURITY_CREDENTIALS, ClientUtil.getProperty("ldap.password"));
            return new InitialLdapContext(env, null);
        } catch (NamingException ne) {
            System.err.println("Error initializing LDAP context");
            System.err.println(ne.getMessage());
            System.exit(1);
        }
        return null; //shouldnt get here
    }

    private SearchControls getSearchControls() {
        SearchControls ctls = new SearchControls();
        String[] attrs = {"sAMAccountName", "givenName", "sn"};
        ctls.setReturningAttributes(attrs);
        return ctls;
    }

}
