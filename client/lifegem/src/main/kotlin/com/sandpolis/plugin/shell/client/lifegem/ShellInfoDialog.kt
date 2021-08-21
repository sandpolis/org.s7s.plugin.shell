package com.sandpolis.client.lifegem.ui.agent_manager

import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ObjectProperty
import javafx.scene.layout.Region
import tornadofx.*

class ShellInfoDialog(val extend: ObjectProperty<Region>) : Fragment() {
    override val root = titledpane("Session Information", collapsible = false) {
        form {
            fieldset {
                field("Shell Type") {
                    label("")
                }
                field("Shell Path") {
                    label("")
                }
                field("Process CPU") {
                    label("")
                }
                field("Process Memory") {
                    label("")
                }
                field("Transmit Rate") {
                    label("")
                }
            }
        }
    }
}