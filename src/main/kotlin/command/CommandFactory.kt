package command

class CommandFactory {

    fun getCommand(key: String): Command {

        return commands.find { it.key == key }
                ?: throw IllegalStateException("Unknown command $key")
    }

    companion object {
        private val commands = setOf(
                Left(),
                Right(),
                Forward()
        )
    }
}