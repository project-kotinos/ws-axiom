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

package org.apache.axiom.om;

import junit.framework.TestCase;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class OMWrapperTest extends TestCase {

    public void testSingleElementWrapper() {
        try {
            String xml = "<root>" +
                    "<wrap1>" +
                    "<wrap3>" +
                    "<wrap2>" +
                    "IncludedText" +
                    "</wrap2>" +
                    "</wrap3>" +
                    "</wrap1>" +
                    "</root>";

            XMLStreamReader xmlStreamReader =
                    XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));
            StAXOMBuilder b = new StAXOMBuilder(xmlStreamReader);

            OMElement documentElement = b.getDocumentElement();
            OMElement wrap2Element =
                    documentElement.getFirstElement().
                            getFirstElement().
                            getFirstElement();

            OMElement elt = OMAbstractFactory.getOMFactory().createOMElement(
                    "testName", "urn:testNs", "ns1"
            );

            elt.addChild(wrap2Element);


            XMLStreamReader reader = wrap2Element.getXMLStreamReaderWithoutCaching();
            int count = 0;
            while (reader.hasNext()) {
                reader.next();
                count ++;
            }

            assertEquals(3, count);
        } catch (XMLStreamException e) {
            fail(e.getMessage());
        }


    }


}
