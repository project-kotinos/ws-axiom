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

package org.apache.ws.commons.om;

/**
 * Interface OMNamespace
 */
public interface OMNamespace {
    /**
     * Method equals.
     *
     * @param uri
     * @param prefix
     * @return Returns boolean.
     */
    public boolean equals(String uri, String prefix);

    /**
     * Method getPrefix.
     *
     * @return Returns String.
     */
    public String getPrefix();

    /**
     * Method getName.
     *
     * @return Returns String.
     */
    public String getName();
    
    /**
     * Returns the OMFactory that created this object
     * @return
     */
    public OMFactory getOMFactory();
}