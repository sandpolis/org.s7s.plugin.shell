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
}

import org.ajoberstar.grgit.Grgit

repositories {
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.0")

	compileOnly(project.getParent()?.getParent()!!)

	compileOnly(project(":com.sandpolis.client.lifegem"))

	compileOnly("no.tornado:tornadofx:2.0.0-SNAPSHOT")

	compileOnly("org.openjfx:javafx-base:16")
	compileOnly("org.openjfx:javafx-graphics:16")
	compileOnly("org.openjfx:javafx-controls:16")
}

eclipse {
	project {
		name = "com.sandpolis.plugin.shell:client:lifegem"
		comment = "com.sandpolis.plugin.shell:client:lifegem"
	}
}

val cloneHterm by tasks.creating {
	enabled = !project.file("build/libapps").exists()

	doLast {
		Grgit.clone {
			dir = project.file("build/libapps")
			uri = "https://chromium.googlesource.com/apps/libapps"
		}
	}
}

val buildHterm by tasks.creating(Exec::class) {
	dependsOn(cloneHterm)
	enabled = !project.file("build/libapps/hterm/dist/js/hterm_all.js").exists()

	commandLine("build/libapps/hterm/bin/mkdist")
}

val copyHterm by tasks.creating(Copy::class) {
	from("build/libapps/hterm/dist/js/hterm_all.js")
	from("build/libapps/hterm/html/hterm.html")

	into("src/main/resources")
}

// Add dependency if necessary
if (! project.file("src/main/resources/hterm.html").exists() || ! project.file("src/main/resources/hterm_all.js").exists()) {
	tasks.findByName("processResources")?.dependsOn(buildHterm)
}
