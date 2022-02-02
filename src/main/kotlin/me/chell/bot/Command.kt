package me.chell.bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class Command {
    abstract val names: Array<String>
    abstract val description: String
    abstract fun exec(input: String, event: MessageReceivedEvent)
}