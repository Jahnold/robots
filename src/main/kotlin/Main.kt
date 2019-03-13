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

        val start = inputManager.getInitialPosition()
        val commands = inputManager.getCommands()

        var position = start
        var result: Grid.Result = Grid.Result.Success(start)
        commands.forEach {command ->
            result = grid.transform(position, command, edges)
            if (result is Grid.Result.Success) {
                position = result.position
            }
            else {
                val edge = Edge(result.position, command)
                edges.add(edge)
                return@forEach
            }
        }

        println("${result.position.x} ${result.position.y} ${result.position.direction.name.substring(0,1)}")
    }


}