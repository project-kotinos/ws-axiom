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
package org.apache.axiom.ts.strategy;

import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMDataSource;
import org.apache.axiom.om.OMSourcedElement;
import org.apache.axiom.om.ds.AbstractPullOMDataSource;
import org.apache.axiom.om.ds.AbstractPushOMDataSource;
import org.apache.axiom.ts.AxiomTestCase;
import org.apache.axiom.ts.strategy.serialization.SerializationStrategy;
import org.junit.Assert;

/**
 * Defines if and how an {@link OMContainer} is to be built or expanded during the execution of a
 * test case.
 */
public interface ExpansionStrategy extends Strategy {
    /**
     * Don't build the {@link OMContainer}.
     */
    ExpansionStrategy DONT_EXPAND = new ExpansionStrategy() {
        public void addTestProperties(AxiomTestCase testCase) {
            testCase.addTestProperty("expand", "no");
        }

        public void apply(OMContainer container) {
            if (container instanceof OMSourcedElement) {
                // Do nothing, but check that the element isn't expanded already
                Assert.assertFalse(((OMSourcedElement)container).isExpanded());
            } else {
                Assert.assertFalse(container.isComplete());
            }
        }

        public boolean isConsumedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return !(pushDS && !serializationStrategy.isPush()) && destructiveDS && !serializationStrategy.isCaching();
        }

        public boolean isExpandedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return pushDS && !serializationStrategy.isPush() || destructiveDS && serializationStrategy.isCaching();
        }
    };
    
    /**
     * Partially build the {@link OMContainer}.
     */
    ExpansionStrategy PARTIAL = new ExpansionStrategy() {
        public void addTestProperties(AxiomTestCase testCase) {
            testCase.addTestProperty("expand", "partially");
        }
        
        public void apply(OMContainer container) {
            container.getFirstOMChild();
            if (container instanceof OMSourcedElement) {
                Assert.assertTrue(((OMSourcedElement)container).isExpanded());
            }
            Assert.assertFalse(container.isComplete());
        }

        public boolean isConsumedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return !serializationStrategy.isCaching();
        }

        public boolean isExpandedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return true;
        }
    };

    /**
     * Fully build the {@link OMContainer}.
     */
    ExpansionStrategy FULL = new ExpansionStrategy() {
        public void addTestProperties(AxiomTestCase testCase) {
            testCase.addTestProperty("expand", "fully");
        }
        
        public void apply(OMContainer container) {
            container.getFirstOMChild();
            container.build();
            if (container instanceof OMSourcedElement) {
                Assert.assertTrue(((OMSourcedElement)container).isExpanded());
            }
            Assert.assertTrue(container.isComplete());
        }

        public boolean isConsumedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return false;
        }

        public boolean isExpandedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy) {
            return true;
        }
    };

    /**
     * Apply the expansion strategy to the given {@link OMContainer}.
     * 
     * @param element
     */
    void apply(OMContainer container);
    
    /**
     * Determines if serializing the {@link OMSourcedElement} after applying this expansion strategy
     * will consume it. If the element is consumed, it cannot be serialized twice.
     * 
     * @param pushDS
     *            specifies whether the {@link OMDataSource} of the {@link OMSourcedElement} extends
     *            {@link AbstractPullOMDataSource} (<code>false</code>) or
     *            {@link AbstractPushOMDataSource} (<code>true</code>)
     * @param destructiveDS
     *            specifies whether the {@link OMDataSource} of the {@link OMSourcedElement} is
     *            destructive
     * @param serializationStrategy
     *            the serialization strategy
     * 
     * @return <code>true</code> if serializing the {@link OMSourcedElement} will consume it,
     *         <code>false</code> if the {@link OMSourcedElement} can be serialized multiple times
     */
    boolean isConsumedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy);
    
    /**
     * Determines if the {@link OMSourcedElement} to which this expansion strategy has been applied
     * will be expanded after serialization.
     * 
     * @param pushDS
     *            specifies whether the {@link OMDataSource} of the {@link OMSourcedElement} extends
     *            {@link AbstractPullOMDataSource} (<code>false</code>) or
     *            {@link AbstractPushOMDataSource} (<code>true</code>)
     * @param destructiveDS
     *            specifies whether the {@link OMDataSource} of the {@link OMSourcedElement} is
     *            destructive
     * @param serializationStrategy
     *            the serialization strategy
     * 
     * @return the expected value of {@link OMSourcedElement#isExpanded()} after serialization
     */
    boolean isExpandedAfterSerialization(boolean pushDS, boolean destructiveDS, SerializationStrategy serializationStrategy);
}
