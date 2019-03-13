package data

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    companion object {
        fun fromString(key: String): Direction {
            return when (key) {
                "N" -> NORTH
                "E" -> EAST
                "S" -> SOUTH
                "W" -> WEST
                else -> throw IllegalArgumentException("Unknown direction $key")
            }
        }

    }
}