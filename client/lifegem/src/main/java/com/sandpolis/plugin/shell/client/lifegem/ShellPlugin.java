package com.sandpolis.plugin.shell.client.lifegem;

import com.sandpolis.client.lifegem.plugin.AgentViewExtension;
import com.sandpolis.client.lifegem.plugin.AgentViewProvider;
import com.sandpolis.core.instance.plugin.SandpolisPlugin;
import com.sandpolis.plugin.shell.client.lifegem.ShellView;

public class ShellPlugin extends SandpolisPlugin implements AgentViewProvider {

	@Override
	public AgentViewExtension[] getAgentViews() {
		return new AgentViewExtension[] { new ShellView() };

	}

}
