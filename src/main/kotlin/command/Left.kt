package command

import data.Direction
import data.Movement

class Left: Command {

    override val key: String = "L"

    override fun transform(direction: Direction): Pair<Direction, Movement> {

        val movement = Movement(0, 0)
        val newDirection = when (direction) {
            Direction.NORTH -> Direction.WEST
            Direction.EAST -> Direction.NORTH
            Direction.SOUTH -> Direction.EAST
            Direction.WEST -> Direction.SOUTH
        }

        return Pair(newDirection, movement)
    }
}