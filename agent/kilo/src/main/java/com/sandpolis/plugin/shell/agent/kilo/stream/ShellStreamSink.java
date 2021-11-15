//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.plugin.shell.agent.kilo.stream;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;

import com.sandpolis.core.net.stream.StreamSink;
import com.sandpolis.plugin.shell.Messages.EV_ShellStreamInput;

public class ShellStreamSink extends StreamSink<EV_ShellStreamInput> {

	private Process process;

	public ShellStreamSink(Process process) {
		checkArgument(process.isAlive());
		this.process = process;
	}

	@Override
	public void onNext(EV_ShellStreamInput item) {
		if (!item.getStdin().isEmpty()) {
			try {
				process.getOutputStream().write(item.getStdin().toByteArray());
				process.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO change terminal size if set
	}
}
