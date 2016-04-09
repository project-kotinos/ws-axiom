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
package org.apache.axiom.util.activation;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that counts the number of bytes written to it and throws an exception when the
 * size exceeds a given limit.
 */
final class SizeLimitedOutputStream extends OutputStream {
    private final long maxSize;
    private long size;
    
    SizeLimitedOutputStream(long maxSize) {
        this.maxSize = maxSize;
    }

    public void write(byte[] b, int off, int len) throws IOException {
        size += len;
        checkSize();
    }

    public void write(byte[] b) throws IOException {
        size += b.length;
        checkSize();
    }

    public void write(int b) throws IOException {
        size++;
        checkSize();
    }
    
    private void checkSize() throws SizeLimitExceededException {
        if (size > maxSize) {
            // Throw a cached exception instance to avoid the overhead of building the
            // stack trace.
            throw SizeLimitExceededException.INSTANCE;
        }
    }
}