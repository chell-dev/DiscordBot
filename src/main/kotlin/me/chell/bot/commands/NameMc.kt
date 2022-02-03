package me.chell.bot.commands

import me.chell.bot.Bot
import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.io.FileNotFoundException
import java.net.URL

class NameMc: Command() {
    override val names = arrayOf("namemc", "names", "NameHistory")
    override val description = "get a minecraft user's name history"

    override fun exec(input: String, event: MessageReceivedEvent) {
        if(input.isEmpty()) {
            event.channel.sendMessage("Usage: ${Bot.prefix}namemc <username>").queue()
            return
        }

        val text = try {
            URL("https://faav.gapple.pw/profiles/$input").readText()
        } catch (e: FileNotFoundException) {
            event.channel.sendMessage("No profile found for $input").queue()
            return
        }

        val s = text.substring(text.indexOf("\"username_history\"") + "\"username_history\": [ ".length, text.indexOf(']')).replace(" ", "")

        val builder = StringBuilder()

        for(line in s.lines()) {
            if(line.startsWith("\"username\":\"")) {
                val last = !line.endsWith(',')

                builder.append(line.substring("\"username\":\"".length).dropLast(if(last) 1 else 2))

                if(!last) builder.append(", ")
            }
        }

        builder.append(" | https://namemc.com/profile/$input")

        event.channel.sendMessage(builder.toString()).queue()
    }
}