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
package org.apache.axiom.ts.dom.element;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axiom.ts.dom.DOMTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Tests the behavior of {@link Node#replaceChild(Node, Node)} when replacing a child by a
 * {@link DocumentFragment}. This test covers the case where the child being replaced is neither the
 * first nor the last child.
 */
public class TestReplaceChildMiddleWithDocumentFragment extends DOMTestCase {
    public TestReplaceChildMiddleWithDocumentFragment(DocumentBuilderFactory dbf) {
        super(dbf);
    }

    protected void runTest() throws Throwable {
        Document document = dbf.newDocumentBuilder().newDocument();
        
        DocumentFragment fragment = document.createDocumentFragment();
        Element x = document.createElementNS(null, "x");
        Element y = document.createElementNS(null, "y");
        fragment.appendChild(x);
        fragment.appendChild(y);
        
        Element element = document.createElementNS(null, "parent");
        Element a = document.createElementNS(null, "a");
        Element b = document.createElementNS(null, "b");
        Element c = document.createElementNS(null, "c");
        element.appendChild(a);
        element.appendChild(b);
        element.appendChild(c);
        
        element.replaceChild(fragment, b);
        
        NodeList children = element.getChildNodes();
        assertEquals(4, children.getLength());
        assertSame(a, children.item(0));
        assertSame(x, children.item(1));
        assertSame(y, children.item(2));
        assertSame(c, children.item(3));
        
        assertSame(element, x.getParentNode());
        assertSame(element, y.getParentNode());
        
        assertNull(fragment.getFirstChild());
        assertNull(fragment.getLastChild());
        assertEquals(0, fragment.getChildNodes().getLength());
    }
}
