package me.chell.bot.commands

import me.chell.bot.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Pfp: Command() {
    override val names = arrayOf("pfp", "avatar")
    override val description = "get a user's avatar"

    override fun exec(input: String, event: MessageReceivedEvent) {

        val user = if(event.message.mentionedUsers.isEmpty()) {
            if(input.isEmpty()) {
                event.channel.sendMessage("Mention a user to get their avatar").queue()
                return
            } else {
                try {
                    event.jda.retrieveUserById(input).complete()
                } catch (e: Exception) {
                    event.channel.sendMessage("Something went wrong").queue()
                    return
                }
            }
        } else {
            event.message.mentionedUsers[0]
        }

        if(user == null) {
            event.channel.sendMessage("Can't find user").queue()
        } else {
            if(user.avatarUrl == null)
                event.channel.sendMessage("This user doesn't have an avatar")
            else
                event.channel.sendMessage(user.avatarUrl!!).queue()
        }

    }
}