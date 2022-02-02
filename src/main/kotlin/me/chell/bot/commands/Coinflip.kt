package me.chell.bot.commands

import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import kotlin.random.Random

class Coinflip: Command() {
    override val names = arrayOf("coinflip", "coin", "cf")
    override val description = "flip a coin"

    override fun exec(input: String, event: MessageReceivedEvent) {
        event.channel.sendMessage(if(Random.nextBoolean()) "Heads" else "Tails").queue()
    }
}