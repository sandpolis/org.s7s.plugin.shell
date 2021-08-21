//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.plugin.shell.agent.kilo.shell;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractShell {

	protected final String location = findShell();

	private String findShell() {

		for (String path : searchPath()) {
			Path p = Paths.get(path);
			if (Files.exists(p) && Files.isExecutable(p))
				return path;
		}
		return null;
	}

	public String getLocation() {
		return location;
	}

	public abstract String[] searchPath();

	public abstract String[] buildSession();

	public abstract String[] buildCommand(String command);
}
