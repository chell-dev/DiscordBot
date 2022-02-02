package me.chell.bot.commands

import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Github: Command() {
    override val names = arrayOf("BecomeSigma", "github")
    override val description = "become a sigma male / female / human instantly"

    override fun exec(input: String, event: MessageReceivedEvent) {
        event.channel.sendMessage("Star every repo you find at https://github.com/chell-dev").queue()
    }
}