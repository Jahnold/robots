@file:Suppress("MemberVisibilityCanBePrivate")

import com.google.common.truth.Truth.assertThat
import command.Forward
import command.Left
import command.Right
import data.Direction
import data.Edge
import data.Position
import org.junit.Before
import org.junit.Test

class GridTest {

    val grid = Grid(5, 3)

    @Before
    fun setUp() {
    }

    @Test
    fun `transform - should return same location with new direction when passed Left`() {

        val start = Position(1 ,1, Direction.NORTH)
        val result = grid.transform(start, Left(), emptyList())

        assertThat(result).isInstanceOf(Grid.Result.Success::class.java)
        assertThat(result.position.x).isEqualTo(start.x)
        assertThat(result.position.y).isEqualTo(start.y)
        assertThat(result.position.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `transform - should return same location with new direction when passed Right`() {

        val start = Position(1 ,1, Direction.NORTH)
        val result = grid.transform(start, Right(), emptyList())

        assertThat(result).isInstanceOf(Grid.Result.Success::class.java)
        assertThat(result.position.x).isEqualTo(start.x)
        assertThat(result.position.y).isEqualTo(start.y)
        assertThat(result.position.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `transform - should return new location with same direction when passed Forward`() {

        val start = Position(1 ,1, Direction.NORTH)
        val result = grid.transform(start, Forward(), emptyList())

        assertThat(result).isInstanceOf(Grid.Result.Success::class.java)
        assertThat(result.position.x).isEqualTo(start.x)
        assertThat(result.position.y).isEqualTo(start.y + 1)
        assertThat(result.position.direction).isEqualTo(Direction.NORTH)
    }

    @Test
    fun `transform - should return Lost when robot is moved off the grid`() {

        val start = Position(1 ,0, Direction.SOUTH)
        val result = grid.transform(start, Forward(), emptyList())

        assertThat(result).isInstanceOf(Grid.Result.Lost::class.java)
    }

    @Test
    fun `transform - should return same location if command matches a known edge`() {

        val start = Position(1 ,0, Direction.SOUTH)
        val edge = Edge(start, Forward())
        val result = grid.transform(start, Forward(), listOf(edge))

        assertThat(result).isInstanceOf(Grid.Result.Success::class.java)
        assertThat(result.position.x).isEqualTo(start.x)
        assertThat(result.position.y).isEqualTo(start.y)
        assertThat(result.position.direction).isEqualTo(start.direction)
    }
}