import command.CommandFactory

object Main {

    private val inputManager = InputManager(CommandFactory())

    @JvmStatic
    fun main(args: Array<String>) {

        val (width, height) = inputManager.getGridSize()
        val grid = Grid(width, height)
        val controller = RobotController(inputManager, grid)

        do {
            val result = controller.controlRobots()
            println(result)

        } while (true)
    }
}