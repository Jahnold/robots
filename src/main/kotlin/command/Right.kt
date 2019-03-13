package command

import data.Direction
import data.Movement

class Right: Command {

    override val key: String = "R"

    override fun transform(direction: Direction): Pair<Direction, Movement> {

        val movement = Movement(0, 0)
        val newDirection = when (direction) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }

        return Pair(newDirection, movement)
    }
}