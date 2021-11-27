module com.sandpolis.plugin.shell.client.lifegem {
	exports com.sandpolis.plugin.shell.client.lifegem;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.web;
	requires jdk.jsobject;
	requires com.sandpolis.client.lifegem;
	requires com.sandpolis.core.instance;
	requires tornadofx;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;

	provides com.sandpolis.core.instance.plugin.SandpolisPlugin
			with com.sandpolis.plugin.shell.client.lifegem.ShellPlugin;
}
