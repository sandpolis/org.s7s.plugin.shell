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
	kotlin("jvm") version "1.6.0"
	id("com.sandpolis.build.module")
}

import org.gradle.internal.os.OperatingSystem
import org.ajoberstar.grgit.Grgit

repositories {
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.+")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.+")

	compileOnly(project.getParent()?.getParent()!!)

	compileOnly(project(":com.sandpolis.client.lifegem"))

	compileOnly("no.tornado:tornadofx:2.0.0-SNAPSHOT")

	if (OperatingSystem.current().isMacOsX()) {
		compileOnly("org.openjfx:javafx-base:17:mac")
		compileOnly("org.openjfx:javafx-graphics:17:mac")
		compileOnly("org.openjfx:javafx-controls:17:mac")
		compileOnly("org.openjfx:javafx-web:17:mac")
	} else if (OperatingSystem.current().isLinux()) {
		compileOnly("org.openjfx:javafx-base:17:linux")
		compileOnly("org.openjfx:javafx-graphics:17:linux")
		compileOnly("org.openjfx:javafx-controls:17:linux")
		compileOnly("org.openjfx:javafx-web:17:linux")
	} else if (OperatingSystem.current().isWindows()) {
		compileOnly("org.openjfx:javafx-base:17:windows")
		compileOnly("org.openjfx:javafx-graphics:17:windows")
		compileOnly("org.openjfx:javafx-controls:17:windows")
		compileOnly("org.openjfx:javafx-web:17:windows")
	}
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
