package me.chell.bot

import me.chell.bot.commands.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object Bot: ListenerAdapter() {

    const val prefix = "\\"

    val commands = listOf(
        Ping(),
        Help(),
        Uwuify(),
        Coinflip(),
        Rng(),
        Pfp(),
        Github()
    )

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message.contentRaw

        if(message.startsWith(prefix, true)) {
            for(command in commands) {
                for(name in command.names) {
                    if(message.substring(1).startsWith(name, true)) {
                        val length = name.length + 2 // prefix + space
                        val s = if(message.length > length) message.substring(length) else ""
                        command.exec(s, event)
                        break
                    }
                }
            }
        } else dadBot(event)
    }

    private fun dadBot(event: MessageReceivedEvent) {
        val message = event.message.contentRaw

        if(message.contains("I'm ", true) && event.author != event.jda.selfUser) {
            if(message.substring(message.lowercase().indexOf("i'm ")).length <= 4) { // no text after "i'm"
                return
            }
            val s = message.substring(message.lowercase().indexOf("i'm ") + 4)
            if("Hi $s, I'm dad.".length < 2000) // message limit
                event.channel.sendMessage("Hi $s, I'm dad.").queue()
        }
    }
}