package me.chell.bot.commands

import me.chell.bot.Bot
import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Help: Command() {
    override val names = arrayOf("help", "h")
    override val description = "print help"

    override fun exec(input: String, event: MessageReceivedEvent) {
        val builder = StringBuilder()
        builder.append("**Prefix**: \"\\${Bot.prefix}\"").append('\n')
        builder.append("**Commands**:").append('\n')
        for(command in Bot.commands) {
            builder.append("${command.names[0]} - ${command.description}").append('\n')
        }

        event.channel.sendMessage(builder.toString()).queue()
    }
}