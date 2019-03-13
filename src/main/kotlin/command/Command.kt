package command

import data.Direction
import data.Movement

interface Command {

    val key: String
    fun transform(direction: Direction): Pair<Direction, Movement>
}