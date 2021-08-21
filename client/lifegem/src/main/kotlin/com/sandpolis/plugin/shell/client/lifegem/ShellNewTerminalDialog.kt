package com.sandpolis.client.lifegem.ui.agent_manager

import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ObjectProperty
import javafx.scene.layout.Region
import tornadofx.Fragment
import tornadofx.titledpane

class ShellNewTerminalDialog(val extend: ObjectProperty<Region>) : Fragment() {
    override val root = titledpane("New Terminal Session", collapsible = false) {

    }
}