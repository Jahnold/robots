import command.Command
import command.CommandFactory
import data.Direction
import data.Position
import java.util.*

class InputManager(private val commandFactory: CommandFactory) {

    fun getGridSize(): List<Int> {

        val scanner = Scanner(System.`in`)
        println()
        println("Please set grid size:")

        val sizeString = scanner.nextLine()
        val sizes = sizeString
                .orEmpty()
                .trim()
                .split(" ")
                .map { it.toInt() }

        if (sizes.size != 2) throw IllegalStateException("Grid size must be two integers")
        return sizes
    }

    fun getInitialPosition(): Position {

        val scanner = Scanner(System.`in`)
        println()
        println("Please set initial position:")

        val positionString = scanner.nextLine()
        val positionDetails = positionString
                .orEmpty()
                .trim()
                .split(" ")

        if (positionDetails.size != 3) throw IllegalStateException("Position must be 3 values")
        val x = positionDetails[0].toInt()
        val y = positionDetails[1].toInt()
        val direction = Direction.fromString(positionDetails[2])

        return Position(x, y, direction)
    }

    fun getCommands(): List<Command> {

        val scanner = Scanner(System.`in`)
        println()
        println("Please input commands:")

        val commandsString = scanner.nextLine()

        return commandsString
                .trim()
                .toUpperCase()
                .chunked(1)
                .map { key -> commandFactory.getCommand(key) }
    }
}