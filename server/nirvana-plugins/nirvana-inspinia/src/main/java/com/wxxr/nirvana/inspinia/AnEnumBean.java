/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wxxr.nirvana.inspinia;

/**
 * A more complicated Enum with additional properties.
 */
public enum AnEnumBean {

	One("A", "B"), Two("C", "D"), Three("E", "F");

	private String propA;
	private String propB;

	AnEnumBean(String propA, String propB) {
		this.propA = propA;
		this.propB = propB;
	}

	public String getPropA() {
		return propA;
	}

	public String getPropB() {
		return propB;
	}
}
