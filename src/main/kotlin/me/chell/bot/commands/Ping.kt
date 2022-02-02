package me.chell.bot.commands

import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Ping: Command() {
    override val names = arrayOf("ping")
    override val description = "pong"

    override fun exec(input: String, event: MessageReceivedEvent) {
        event.channel.sendMessage("pong").queue()
    }
}