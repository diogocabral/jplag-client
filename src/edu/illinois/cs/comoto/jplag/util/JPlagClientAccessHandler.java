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

/*
 * Created on 15.03.2005
 * 
 * For more information about SOAP headers see:
 *   http://java.sun.com/webservices/docs/1.3/tutorial/doc/JAXRPC7.html#wp122942
 */
package edu.illinois.cs.comoto.jplag.util;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.*;

/**
 * @author Moritz Kroll
 */
public class JPlagClientAccessHandler extends GenericHandler {
    public static final int compatibilityLevel = 4;
    protected HandlerInfo info = null;

    /*
      * Access information used to build up the Access header element
      */

    protected String username = null;
    protected String password = null;

    /**
     * Return the headers given by the info HandlerInfo object
     */
    public QName[] getHeaders() {
        return info.getHeaders();
    }

    /**
     * Save the HandlerInfo object
     */
    public void init(HandlerInfo arg) {
        info = arg;
    }

    /**
     * Used to set the username and password
     * Use something like the following to access this function:
     * <p/>
     * private JPlagClientAccessHandler accessHandler=null;
     * <p/>
     * [...]
     * <p/>
     * HandlerChain handlerchain=stub._getHandlerChain();
     * Iterator handlers=handlerchain.iterator();
     * while(handlers.hasNext())
     * {
     * Handler handler=(Handler) handlers.next();
     * if(handler instanceof JPlagClientAccessHandler)
     * {
     * accessHandler=((JPlagClientAccessHandler)handler);
     * break;
     * }
     * }
     * if(accessHandler!=null)
     * {
     * accessHandler.setUserPassObjects(getJUsernameField().getText(),
     * getJPasswordField().getText());
     * }
     */
    public void setUserPassObjects(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Adds an "Access" element to the SOAP header
     */
    public boolean handleRequest(MessageContext msgct) {
        if (msgct instanceof SOAPMessageContext) {
            SOAPMessageContext smsgct = (SOAPMessageContext) msgct;
            try {
                SOAPMessage msg = smsgct.getMessage();
                SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
                SOAPHeader header = msg.getSOAPHeader();

                if (header == null)
                    header = envelope.addHeader(); // add an header if non exists

                SOAPHeaderElement accessElement = header.addHeaderElement(
                        envelope.createName("Access", "ns0",
                                "http://www.ipd.uni-karlsruhe.de/jplag/types"));
                SOAPElement usernameelem = accessElement.addChildElement(
                        "username");
                usernameelem.addTextNode(username);
                SOAPElement passwordelem = accessElement.addChildElement(
                        "password");
                passwordelem.addTextNode(password);
                SOAPElement compatelem = accessElement.addChildElement(
                        "compatLevel");
                compatelem.addTextNode(compatibilityLevel + "");
            } catch (SOAPException x) {
                System.out.println("Unable to create access SOAP header!");
                x.printStackTrace();
            }
        }
        return true;
    }
}
