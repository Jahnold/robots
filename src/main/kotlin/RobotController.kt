import data.Direction
import data.Edge

class RobotController(
        private val inputManager: InputManager,
        private val grid: Grid
) {

    private val edges = mutableListOf<Edge>()

    fun controlRobots(): String {

        val start = inputManager.getInitialPosition()
        val commands = inputManager.getCommands()

        var position = start
        var result: Grid.Result = Grid.Result.Success(start)
        for (command in commands) {
            result = grid.transform(position, command, edges)
            position = result.position
            if (result is Grid.Result.Lost) {
                val edge = Edge(result.position, command)
                edges.add(edge)
                break
            }
        }

        return getResultString(result)
    }

    private fun getResultString(result: Grid.Result): String {
        val lost = if (result is Grid.Result.Lost) "LOST" else ""
        return "${result.position.x} ${result.position.y} ${result.position.direction.name.substring(0, 1)} $lost".trim()
    }
}