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

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PwshShell extends AbstractShell {

	@Override
	public String[] searchPath() {
		return new String[] { "/usr/bin/pwsh", "C:/Windows/System32/WindowsPowerShell/v1.0/powershell.exe",
				"C:/Windows/SysWOW64/WindowsPowerShell/v1.0/powershell.exe" };
	}

	@Override
	public String[] buildSession() {
		return new String[] { location };
	}

	@Override
	public String[] buildCommand(String command) {
		return new String[] { location, "-encodedCommand",
				Base64.getEncoder().encodeToString(command.getBytes(StandardCharsets.UTF_16LE)) };
	}
}
