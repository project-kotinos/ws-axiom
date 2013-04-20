/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ws.commons.soap;

public class SOAPFaultSubCodeTestCase extends SOAPFaultCodeTestCase {
    protected SOAPFaultValue soap11FaultValue;
    protected SOAPFaultValue soap12FaultValueInFaultCode;
    protected SOAPFaultSubCode soap12FaultSubCodeInCode;

    protected SOAPFaultValue soap12FaultValueInFaultSubCode;
    protected SOAPFaultSubCode soap12FaultSubCodeInSubCode;

    protected SOAPFaultValue soap11FaultValueWithParser;
    protected SOAPFaultValue soap12FaultValueInFaultCodeWithParser;
    protected SOAPFaultSubCode soap12FaultSubCodeInFaultCodeWithParser;

    protected SOAPFaultValue soap12FaultValueInFaultSubCodeWithParser;
    protected SOAPFaultSubCode soap12FaultSubCodeInSubCodeWithParser;

    public SOAPFaultSubCodeTestCase(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
        soap11FaultValue = soap11Factory.createSOAPFaultValue(soap11FaultCode);

        soap12FaultValueInFaultCode =
                soap12Factory.createSOAPFaultValue(soap12FaultCode);
        soap12FaultSubCodeInCode =
                soap12Factory.createSOAPFaultSubCode(soap12FaultCode);


        soap12FaultSubCodeInSubCode =
                soap12Factory.createSOAPFaultSubCode(soap12FaultSubCodeInCode);

        soap11FaultValueWithParser = soap11FaultCodeWithParser.getValue();
        soap12FaultValueInFaultCodeWithParser =
                soap12FaultCodeWithParser.getValue();
        soap12FaultSubCodeInFaultCodeWithParser =
                soap12FaultCodeWithParser.getSubCode();

        soap12FaultValueInFaultSubCodeWithParser =
                soap12FaultSubCodeInFaultCodeWithParser.getValue();
        soap12FaultSubCodeInSubCodeWithParser =
                soap12FaultSubCodeInFaultCodeWithParser.getSubCode();
    }
}