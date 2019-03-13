package command

import data.Direction
import data.Movement

class Forward: Command {

    override val key: String = "F"

    override fun transform(direction: Direction): Pair<Direction, Movement> {

        val movement = when (direction) {

            Direction.NORTH -> Movement(0, 1)
            Direction.EAST -> Movement(1, 0)
            Direction.SOUTH -> Movement(0, -1)
            Direction.WEST -> Movement(-1, 0)
        }
        return Pair(direction, movement)
    }
}