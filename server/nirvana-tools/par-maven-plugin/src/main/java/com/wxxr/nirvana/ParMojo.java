package com.wxxr.nirvana;

/*
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

import java.io.File;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * Build a PAR from the current project.
 */
@Mojo(name = "par", requiresProject = true, threadSafe = true, defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class ParMojo extends AbstractParMojo {

	/**
	 * Directory containing the classes and resource files that should be
	 * packaged into the JAR.
	 */
	@Parameter(defaultValue = "${project.build.outputDirectory}", required = true)
	private File classesDirectory;

	/**
	 * Classifier to add to the artifact generated. If given, the artifact will
	 * be attached. If this is not given,it will merely be written to the output
	 * directory according to the finalName.
	 */
	@Parameter(property = "maven.jar.classifier", defaultValue = "")
	private String classifier;

	protected String getClassifier() {
		return classifier;
	}

	/**
	 * @return type of the generated artifact
	 */
	protected String getType() {
		return "par";
	}

	/**
	 * Return the main classes directory, so it's used as the root of the jar.
	 */
	protected File getClassesDirectory() {
		return classesDirectory;
	}
}
