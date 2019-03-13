import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.whenever
import command.Forward
import command.Left
import command.Right
import data.Direction
import data.Position
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RobotControllerTest {

    @Mock lateinit var inputManager: InputManager
    lateinit var controller: RobotController
    val grid = Grid(5, 3)

    @Before
    fun setUp() {
        controller = RobotController(inputManager, grid)
    }

    @Test
    fun `controlRobots - should pass the sample data`() {

        whenever(inputManager.getInitialPosition()).thenReturn(Position(1, 1, Direction.EAST))
        whenever(inputManager.getCommands()).thenReturn(FIRST_COMMANDS)

        val result1 = controller.controlRobots()

        whenever(inputManager.getInitialPosition()).thenReturn(Position(3, 2, Direction.NORTH))
        whenever(inputManager.getCommands()).thenReturn(SECOND_COMMANDS)

        val result2 = controller.controlRobots()

        whenever(inputManager.getInitialPosition()).thenReturn(Position(0, 3, Direction.WEST))
        whenever(inputManager.getCommands()).thenReturn(THIRD_COMMANDS)

        val result3 = controller.controlRobots()

        assertThat(result1).isEqualTo("1 1 E")
        assertThat(result2).isEqualTo("3 3 N LOST")
        assertThat(result3).isEqualTo("2 3 S")
    }

    companion object {
        val FIRST_COMMANDS = listOf(Right(), Left(), Right(), Left(), Right(), Left(), Right(), Left())
        val SECOND_COMMANDS = listOf(Forward(), Right(), Right(), Forward(), Left(), Left(), Forward(), Forward(), Right(), Right(), Forward(), Left(), Left())
        val THIRD_COMMANDS = listOf(Left(), Left(), Forward(), Forward(), Forward(), Left(), Forward(), Left(), Forward(), Left())
    }
}