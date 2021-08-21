package com.sandpolis.plugin.shell.client.lifegem

import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.plugin.shell.client.lifegem.TerminalView
import com.sandpolis.client.lifegem.plugin.AgentViewExtension
import tornadofx.*

class ShellView : AgentViewExtension("Shell") {
    override fun nowVisible(profile: STDocument) {
    }

    override fun nowInvisible() {
    }

    val term = TerminalView()

    override val root = titledpane("Shell") {
        content = term
    }

    init {
        term.prefWidthProperty().bind(this.root.widthProperty())
        term.prefHeightProperty().bind(this.root.heightProperty())
    }
}