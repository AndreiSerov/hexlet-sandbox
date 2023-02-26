package org.example.ui

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

/**
 * @author andreiserov
 */
class HelloFX : Application() {
    override fun start(stage: Stage) {
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")
        val l = Label("Hello, JavaFX $javafxVersion, running on Java $javaVersion.")
        val rectangle = Rectangle().apply {
            x = .0
            y = .0
            width = 100.9
            height = 100.9
        }

        val rectangles = (1..7).map {
            Rectangle(370 - 15.0*it, 140.0 + 15*it, 30.0 * it, 15.0)
                .apply {
                    fill = listOf(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
                        Color.AQUAMARINE, Color.BLUE, Color.PURPLE
                    ).reversed()[it - 1]
                }
        }

        val root = Group(rectangles)
        val scene = Scene(root, 640.0, 480.0)
        stage.scene = scene
        stage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(HelloFX::class.java, *args)
        }
    }

    /**
     *
     * /Users/andreiserov/.sdkman/candidates/java

    --module-path "/Users/andreiserov/.sdkman/candidates/java/javafx-sdk-19.0.2.1/lib" --add-modules javafx.controls,javafx.fxml
     */
}

