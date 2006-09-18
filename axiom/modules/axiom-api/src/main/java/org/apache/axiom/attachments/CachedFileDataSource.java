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

package org.apache.axiom.attachments;

import java.io.File;

import javax.activation.FileDataSource;

public class CachedFileDataSource extends FileDataSource {

	String contentType = null;

	public CachedFileDataSource(File arg0) {
		super(arg0);
	}

	public String getContentType() {
		if (this.contentType != null) {
			return contentType;
		} else {
			return super.getContentType();
		}
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
