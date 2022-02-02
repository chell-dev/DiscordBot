package me.chell.bot.commands

import me.chell.bot.Command
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.net.HttpURLConnection
import java.net.URL

class Server: Command() {
    override val names = arrayOf("server")
    override val description = "get a url's response code"

    override fun exec(input: String, event: MessageReceivedEvent) {
        if(event.member == null) return
        if(!event.member!!.hasPermission(Permission.ADMINISTRATOR)) {
            event.channel.sendMessage("No ip logging!").queue()
            return
        }
        if(input.isEmpty()) {
            event.channel.sendMessage("gib url to check").queue()
            return
        }
        try {
            event.channel.sendMessage("Checking...").queue()
            val connection = URL(input).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()
            event.channel.sendMessage("${connection.responseCode} - ${connection.responseMessage}").queue()
        } catch (e: Exception) {
            event.channel.sendMessage("Serber is unreachable").queue()
        }
    }
}