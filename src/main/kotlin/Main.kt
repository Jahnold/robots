import command.CommandFactory
import data.Edge

object Main {

    private lateinit var grid: Grid

    private val inputManager = InputManager(CommandFactory())
    private val edges = mutableListOf<Edge>()

    @JvmStatic
    fun main(args: Array<String>) {

        val (width, height) = inputManager.getGridSize()
        grid = Grid(width, height)

        do {
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

            val lost = if (result is Grid.Result.Lost) "LOST" else ""
            println("${result.position.x} ${result.position.y} ${result.position.direction.name.substring(0, 1)} $lost")

        } while (true)
    }
}