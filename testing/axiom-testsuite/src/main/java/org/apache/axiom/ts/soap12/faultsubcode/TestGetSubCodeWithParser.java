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
package org.apache.axiom.ts.soap12.faultsubcode;

import org.apache.axiom.om.OMMetaFactory;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFaultSubCode;
import org.apache.axiom.ts.soap.SOAPSample;
import org.apache.axiom.ts.soap.SampleBasedSOAPTestCase;

public class TestGetSubCodeWithParser extends SampleBasedSOAPTestCase {
    public TestGetSubCodeWithParser(OMMetaFactory metaFactory) {
        super(metaFactory, SOAPSample.SOAP12_FAULT);
    }

    @Override
    protected void runTest(SOAPEnvelope envelope) throws Throwable {
        SOAPFaultSubCode subCode = envelope.getBody().getFault().getCode().getSubCode();
        assertNotNull(
                "SOAP 1.2 SOAPFaultSubCode Test In FaultCode With Parser : - getSubCode method returns null",
                subCode.getSubCode());
        assertEquals(
                "SOAP 1.2 SOAPFaultSubCode Test In FaultCode With Parser : - SubCode local name mismatch",
                SOAP12Constants.SOAP_FAULT_SUB_CODE_LOCAL_NAME, subCode.getSubCode().getLocalName());
    }
}
