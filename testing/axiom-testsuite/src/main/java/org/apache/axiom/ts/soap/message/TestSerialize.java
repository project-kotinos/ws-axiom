/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axiom.ts.soap.message;

import org.apache.axiom.om.OMMetaFactory;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.soap.SOAPMessage;
import org.apache.axiom.ts.AxiomTestCase;
import org.apache.axiom.ts.dimension.ExpansionStrategy;
import org.apache.axiom.ts.dimension.serialization.SerializationStrategy;
import org.apache.axiom.ts.soap.TestMessage;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.InputSource;

public class TestSerialize extends AxiomTestCase {
    private final TestMessage testMessage;
    private final ExpansionStrategy expansionStrategy;
    private final SerializationStrategy serializationStrategy;

    public TestSerialize(OMMetaFactory metaFactory, TestMessage testMessage,
            ExpansionStrategy expansionStrategy, SerializationStrategy serializationStrategy) {
        super(metaFactory);
        this.testMessage = testMessage;
        this.expansionStrategy = expansionStrategy;
        this.serializationStrategy = serializationStrategy;
        addTestParameter("message", testMessage.getName());
        expansionStrategy.addTestParameters(this);
        serializationStrategy.addTestParameters(this);
    }

    protected void runTest() throws Throwable {
        SOAPMessage message = OMXMLBuilderFactory.createSOAPModelBuilder(metaFactory,
                testMessage.getInputStream(), null).getSOAPMessage();
        expansionStrategy.apply(message);
        XMLAssert.assertXMLIdentical(XMLUnit.compareXML(
                new InputSource(testMessage.getInputStream()),
                serializationStrategy.serialize(message).getInputSource()), true);
        message.close(false);
    }
}