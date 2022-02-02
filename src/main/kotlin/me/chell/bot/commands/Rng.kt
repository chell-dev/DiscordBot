package me.chell.bot.commands

import me.chell.bot.Bot
import me.chell.bot.Command
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import kotlin.random.Random

class Rng: Command() {
    override val names = arrayOf("random", "rand", "rng")
    override val description = "get a random number"

    override fun exec(input: String, event: MessageReceivedEvent) {
        if(input.isEmpty()) {
            printUsage(event.channel)
            return
        }
        val split = input.split(" ")
        if(split.size != 2) {
            printUsage(event.channel)
            return
        }

        try {
            event.channel.sendMessage(Random.nextInt(Integer.parseInt(split[0]), Integer.parseInt(split[1]) + 1).toString()).queue()
        } catch (e: Exception) {
            printUsage(event.channel)
        }

    }

    private fun printUsage(channel: MessageChannel) {
        channel.sendMessage("Usage: ${Bot.prefix}random <min> <max>").queue()
    }
}