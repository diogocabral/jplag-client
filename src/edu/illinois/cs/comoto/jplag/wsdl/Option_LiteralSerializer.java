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

// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package edu.illinois.cs.comoto.jplag.wsdl;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.literal.LiteralObjectSerializerBase;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;

import javax.xml.namespace.QName;
import java.util.ArrayList;

public class Option_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable {
    private static final javax.xml.namespace.QName ns1_language_QNAME = new QName("", "language");
    private static final javax.xml.namespace.QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
    private static final javax.xml.namespace.QName ns1_comparisonMode_QNAME = new QName("", "comparisonMode");
    private static final javax.xml.namespace.QName ns2_int_TYPE_QNAME = SchemaConstants.QNAME_TYPE_INT;
    private CombinedSerializer ns2_myns2__int__java_lang_Integer_Int_Serializer;
    private static final javax.xml.namespace.QName ns1_minimumMatchLength_QNAME = new QName("", "minimumMatchLength");
    private CombinedSerializer ns2_myns2__int__int_Int_Serializer;
    private static final javax.xml.namespace.QName ns1_suffixes_QNAME = new QName("", "suffixes");
    private static final javax.xml.namespace.QName ns1_readSubdirs_QNAME = new QName("", "readSubdirs");
    private static final javax.xml.namespace.QName ns2_boolean_TYPE_QNAME = SchemaConstants.QNAME_TYPE_BOOLEAN;
    private CombinedSerializer ns2_myns2__boolean__boolean_Boolean_Serializer;
    private static final javax.xml.namespace.QName ns1_pathToFiles_QNAME = new QName("", "pathToFiles");
    private static final javax.xml.namespace.QName ns1_basecodeDir_QNAME = new QName("", "basecodeDir");
    private static final javax.xml.namespace.QName ns1_storeMatches_QNAME = new QName("", "storeMatches");
    private static final javax.xml.namespace.QName ns1_clustertype_QNAME = new QName("", "clustertype");
    private static final javax.xml.namespace.QName ns1_countryLang_QNAME = new QName("", "countryLang");
    private static final javax.xml.namespace.QName ns1_title_QNAME = new QName("", "title");
    private static final javax.xml.namespace.QName ns1_originalDir_QNAME = new QName("", "originalDir");

    public Option_LiteralSerializer(javax.xml.namespace.QName type, java.lang.String encodingStyle) {
        this(type, encodingStyle, false);
    }

    public Option_LiteralSerializer(javax.xml.namespace.QName type, java.lang.String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }

    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns2_myns2_string__java_lang_String_String_Serializer = (CombinedSerializer) registry.getSerializer("", java.lang.String.class, ns2_string_TYPE_QNAME);
        ns2_myns2__int__java_lang_Integer_Int_Serializer = (CombinedSerializer) registry.getSerializer("", java.lang.Integer.class, ns2_int_TYPE_QNAME);
        ns2_myns2__int__int_Int_Serializer = (CombinedSerializer) registry.getSerializer("", int.class, ns2_int_TYPE_QNAME);
        ns2_myns2__boolean__boolean_Boolean_Serializer = (CombinedSerializer) registry.getSerializer("", boolean.class, ns2_boolean_TYPE_QNAME);
    }

    public java.lang.Object doDeserialize(XMLReader reader,
                                          SOAPDeserializationContext context) throws java.lang.Exception {
        edu.illinois.cs.comoto.jplag.wsdl.Option instance = new edu.illinois.cs.comoto.jplag.wsdl.Option();
        java.lang.Object member = null;
        javax.xml.namespace.QName elementName;
        java.util.List values;
        java.lang.Object value;

        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_language_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_language_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setLanguage((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_language_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_comparisonMode_QNAME)) {
                member = ns2_myns2__int__java_lang_Integer_Int_Serializer.deserialize(ns1_comparisonMode_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setComparisonMode((java.lang.Integer) member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_minimumMatchLength_QNAME)) {
                member = ns2_myns2__int__int_Int_Serializer.deserialize(ns1_minimumMatchLength_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setMinimumMatchLength(((java.lang.Integer) member).intValue());
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_minimumMatchLength_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_suffixes_QNAME))) {
            values = new ArrayList();
            for (; ; ) {
                elementName = reader.getName();
                if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_suffixes_QNAME))) {
                    value = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_suffixes_QNAME, reader, context);
                    if (value == null) {
                        throw new DeserializationException("literal.unexpectedNull");
                    }
                    values.add(value);
                    reader.nextElementContent();
                } else {
                    break;
                }
            }
            member = new java.lang.String[values.size()];
            member = values.toArray((Object[]) member);
            instance.setSuffixes((java.lang.String[]) member);
        } else {
            instance.setSuffixes(new java.lang.String[0]);
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_readSubdirs_QNAME)) {
                member = ns2_myns2__boolean__boolean_Boolean_Serializer.deserialize(ns1_readSubdirs_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setReadSubdirs(((Boolean) member).booleanValue());
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_readSubdirs_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_pathToFiles_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_pathToFiles_QNAME, reader, context);
                instance.setPathToFiles((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_pathToFiles_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_basecodeDir_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_basecodeDir_QNAME, reader, context);
                instance.setBasecodeDir((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_basecodeDir_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_storeMatches_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_storeMatches_QNAME, reader, context);
                instance.setStoreMatches((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_storeMatches_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_clustertype_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_clustertype_QNAME, reader, context);
                instance.setClustertype((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_clustertype_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_countryLang_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_countryLang_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setCountryLang((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_countryLang_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_title_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_title_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setTitle((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_title_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_originalDir_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_originalDir_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setOriginalDir((java.lang.String) member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[]{ns1_originalDir_QNAME, reader.getName()});
            }
        } else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }

        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object) instance;
    }

    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        edu.illinois.cs.comoto.jplag.wsdl.Option instance = (edu.illinois.cs.comoto.jplag.wsdl.Option) obj;

    }

    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        edu.illinois.cs.comoto.jplag.wsdl.Option instance = (edu.illinois.cs.comoto.jplag.wsdl.Option) obj;

        if (instance.getLanguage() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getLanguage(), ns1_language_QNAME, null, writer, context);
        if (instance.getComparisonMode() != null) {
            ns2_myns2__int__java_lang_Integer_Int_Serializer.serialize(instance.getComparisonMode(), ns1_comparisonMode_QNAME, null, writer, context);
        }
        if (new java.lang.Integer(instance.getMinimumMatchLength()) == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2__int__int_Int_Serializer.serialize(new java.lang.Integer(instance.getMinimumMatchLength()), ns1_minimumMatchLength_QNAME, null, writer, context);
        if (instance.getSuffixes() != null) {
            for (int i = 0; i < instance.getSuffixes().length; ++i) {
                ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getSuffixes()[i], ns1_suffixes_QNAME, null, writer, context);
            }
        }
        if (new Boolean(instance.isReadSubdirs()) == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2__boolean__boolean_Boolean_Serializer.serialize(new Boolean(instance.isReadSubdirs()), ns1_readSubdirs_QNAME, null, writer, context);
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getPathToFiles(), ns1_pathToFiles_QNAME, null, writer, context);
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getBasecodeDir(), ns1_basecodeDir_QNAME, null, writer, context);
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getStoreMatches(), ns1_storeMatches_QNAME, null, writer, context);
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getClustertype(), ns1_clustertype_QNAME, null, writer, context);
        if (instance.getCountryLang() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getCountryLang(), ns1_countryLang_QNAME, null, writer, context);
        if (instance.getTitle() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getTitle(), ns1_title_QNAME, null, writer, context);
        if (instance.getOriginalDir() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getOriginalDir(), ns1_originalDir_QNAME, null, writer, context);
    }
}
