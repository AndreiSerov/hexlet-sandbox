package org.example.ui

import javafx.application.Application
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import org.example.ui.HanoiTower.Companion.selectedCircle
import java.lang.Thread.sleep
import java.util.DoubleSummaryStatistics

/**
 * @author andreiserov
 */

class HanoiTower : Application() {

    override fun start(primaryStage: Stage?) {

        primaryStage?.scene = Scene(createContent())
        primaryStage?.show()
    }

    private fun createContent(): Parent {
        val root = Pane()
        val threeTowers = Pane()
            .apply {
                setPrefSize(400 * 3.0, 300.0)

                (0..2).forEach {
                    val tower = Tower(it + 1, it * 400.0, 150.0)

                    if (it == 0) {
                        for (j in NUM_CIRCLES downTo 1) {
                            val color = listOf(
                                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
                                Color.AQUAMARINE, Color.BLUE, Color.PURPLE
                            ).reversed()[j - 1]
                            val circle = HanoiCircle(NUM_CIRCLES - j + 1, (30 + j * 20).toDouble(), color)
                            circle.stroke = Color.BLACK

                            circle.strokeWidth = 3.0
                            tower.addCircle(circle)
                        }
                    }
                    children.add(tower)
                }
            }


        val button = Button()
            .apply {
                setOnMouseClicked { autoComplete(
                    (threeTowers.children[0] as Tower).getTopMost()!!,
                    threeTowers.children[0] as Tower,
                    threeTowers.children[2] as Tower,
                    threeTowers.children[1] as Tower,
                ) }
            }

        root.children.add(threeTowers)
        root.children.add(button)

        return root
    }

    private fun autoComplete(n: HanoiCircle, source: Tower, destination: Tower, auxilary: Tower) {
//        sleep(3000)
        val towerIds = listOf(source, destination, auxilary).map { it.id to it.children }

        if (n.id == 0) {
            return
        }

        val selected = source.getCircleById(n.id - 1) ?: return
        autoComplete(selected, source, auxilary, destination)
        println("Move disk ${n.id} from source ${source.id} to destination ${destination.id}")
        destination.addCircle(n)
        autoComplete(selected, auxilary, destination, source)
    }

    companion object {

        var NUM_CIRCLES: Int = 3
        internal var selectedCircle: HanoiCircle? = null

        @JvmStatic
        fun main(args: Array<String>) {
            launch(HanoiTower::class.java, *args)
        }
    }
}

internal class HanoiCircle(val id: Int, radius: Double, fill: Paint) : Circle(radius, fill)

internal class Tower(val id: Int) : StackPane() {
    constructor(id: Int, x: Double, y: Double) : this(id) {
        translateX = x
        translateY = y
        setPrefSize(400.0, 400.0)

        val bg = Rectangle(30.0, 30.0)
        this.setOnMouseClicked {
            println("Click on tower $id")

            selectedCircle = if (selectedCircle != null) {
                addCircle(selectedCircle!!)
                null
            } else {
                getTopMost()
            }
        }

        children.add(bg)
    }

    fun getTopMost(): HanoiCircle? {
        return children.stream()
            .filter { n: Node? -> n is HanoiCircle }
            .map { n: Node? -> n as HanoiCircle? }
            .min(Comparator.comparingDouble { obj -> obj?.radius!! })
            .orElse(null)
    }

    fun getCircleById(id: Int): HanoiCircle? {
        return children
            .filterIsInstance<HanoiCircle>()
            .firstOrNull { it.id == id }
    }

    fun addCircle(circle: HanoiCircle) {
        val topMost = getTopMost()

        if (topMost == null) {
            children.add(circle)
        } else {
            if (circle.radius < topMost.radius) {
                children.add(circle)
            }
        }
    }
}