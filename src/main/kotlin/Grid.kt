import command.Command
import data.Edge
import data.Position

class Grid(private val width: Int, private val height: Int) {

    fun transform(start: Position, command: Command, edges: List<Edge>): Result {

        if (isPreviousLostLocation(start, command, edges)) return Result.Success(start)

        val (direction, movement) = command.transform(start.direction)

        val newX = start.x + movement.x
        val newY = start.y + movement.y
        val newPosition = Position(newX, newY, direction)

        return when (isPositionInGrid(newPosition, width, height)) {
            true -> Result.Success(newPosition)
            else -> Result.Lost(start)
        }
    }

    private fun isPreviousLostLocation(position: Position, command: Command, edges: List<Edge>): Boolean {

        edges.forEach { edge ->
            if (edge.position == position && edge.command::class == command::class) return true
        }
        return false
    }

    private fun isPositionInGrid(position: Position, gridX: Int, gridY: Int): Boolean {

        val isInX = position.x in 0..gridX
        val isInY = position.y in 0..gridY

        return isInX && isInY
    }

    sealed class Result(val position: Position) {
        class Success(position: Position): Result(position)
        class Lost(position: Position): Result(position)
    }
}