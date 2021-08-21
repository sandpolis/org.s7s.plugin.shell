//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

plugins {
	id("java-library")
	id("sandpolis-java")
	id("sandpolis-module")
	id("sandpolis-soi")

	id("org.openjfx.javafxplugin") version "0.0.10"
	kotlin("jvm") version "1.5.20"
}

repositories {
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")

	compileOnly(project.getParent()?.getParent()!!)

	compileOnly(project(":com.sandpolis.client.lifegem"))

	implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
}

javafx {
	modules = listOf( "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.web", "javafx.swing" )
	version = "16"
}

eclipse {
	project {
		name = "com.sandpolis.plugin.shell:client:lifegem"
		comment = "com.sandpolis.plugin.shell:client:lifegem"
	}
}
