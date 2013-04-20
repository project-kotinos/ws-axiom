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

package org.apache.ws.commons.om.impl.traverse;

import org.apache.ws.commons.om.OMAttribute;
import org.apache.ws.commons.om.OMElement;
import org.apache.ws.commons.om.OMNode;

import javax.xml.namespace.QName;

/**
 * Class OMChildrenWithSpecificAttributeIterator
 */
public class OMChildrenWithSpecificAttributeIterator
        extends OMChildrenIterator {
    /**
     * Field attributeName
     */
    private QName attributeName;

    /**
     * Field attributeValue
     */
    private String attributeValue;

    /**
     * Field detach
     */
    private boolean detach;

    /**
     * Constructor OMChildrenWithSpecificAttributeIterator.
     *
     * @param currentChild
     * @param attributeName
     * @param attributeValue
     * @param detach
     */
    public OMChildrenWithSpecificAttributeIterator(OMNode currentChild,
                                                   QName attributeName,
                                                   String attributeValue,
                                                   boolean detach) {
        super(currentChild);
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.detach = detach;
    }

    /**
     * Method hasNext.
     *
     * @return Returns boolean.
     */
    public boolean hasNext() {

        // First check whether we have a child, using the super class
        if (currentChild == null) {
            return false;
        }
        boolean isMatchingNodeFound = false;
        boolean needToMoveForward = true;

        // now we have a child to check. If its an OMElement and matches the criteria, then we are done
        while (needToMoveForward) {

            // check the current node for the criteria
            if (currentChild instanceof OMElement) {
                OMAttribute attr =
                        ((OMElement) currentChild).getAttribute(
                                attributeName);
                if ((attr != null)
                        && attr.getAttributeValue().equalsIgnoreCase(attributeValue)) {
                    isMatchingNodeFound = true;
                    needToMoveForward = false;
                } else {
                    currentChild = currentChild.getNextOMSibling();
                    needToMoveForward = !(currentChild == null);
                }
            } else {

                // get the next named node
                currentChild = currentChild.getNextOMSibling();
                needToMoveForward = !(currentChild == null);
            }
        }
        return isMatchingNodeFound;
    }

    /**
     * Method next.
     *
     * @return Returns Object.
     */
    public Object next() {
        nextCalled = true;
        removeCalled = false;
        lastChild = currentChild;
        currentChild = currentChild.getNextOMSibling();
        if ((lastChild != null) && detach && lastChild.getParent() != null) {
            lastChild.detach();
        }
        return lastChild;
    }
}