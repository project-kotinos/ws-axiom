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

package org.apache.ws.commons.soap.impl.llom;

import org.apache.ws.commons.om.OMContainer;
import org.apache.ws.commons.om.OMElement;
import org.apache.ws.commons.om.OMNamespace;
import org.apache.ws.commons.om.OMXMLParserWrapper;
import org.apache.ws.commons.om.impl.llom.OMElementImpl;
import org.apache.ws.commons.soap.SOAPFactory;
import org.apache.ws.commons.soap.SOAPProcessingException;

public abstract class SOAPElement extends OMElementImpl {

    /**
     * @param parent
     * @param localName
     * @param extractNamespaceFromParent
     */
    protected SOAPElement(OMElement parent,
                          String localName,
                          boolean extractNamespaceFromParent,
                          SOAPFactory factory) throws SOAPProcessingException {
        super(localName, null, parent, factory);
        if (parent == null) {
            throw new SOAPProcessingException(
                    " Can not create " + localName +
                            " element without a parent !!");
        }
        checkParent(parent);

        if (extractNamespaceFromParent) {
            this.ns = parent.getNamespace();
        }
    }


    protected SOAPElement(OMElement parent,
                          String localName,
                          OMXMLParserWrapper builder,
                          SOAPFactory factory) {
        super(localName, null, parent, builder, factory);
    }

    /**
     * @param localName
     * @param ns
     */
    protected SOAPElement(String localName, OMNamespace ns, 
            SOAPFactory factory) {
        super(localName, ns, factory);
    }

    /**
     * This has to be implemented by all the derived classes to check for the correct parent.
     */
    protected abstract void checkParent(OMElement parent) throws SOAPProcessingException;

    public void setParent(OMContainer element) {
        super.setParent(element);

        if (element instanceof OMElement) {
            checkParent((OMElement) element);
        }
    }


}