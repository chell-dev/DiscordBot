package me.chell.bot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.io.File

fun main() {
    val file = File("token.txt")

    if(!file.exists()) {
        file.createNewFile()
        println("Put your token in token.txt")
        return
    }

    val token = file.readText()

    if(token.isEmpty()) {
        println("Put your token in token.txt")
        return
    }

    val jda = JDABuilder.createDefault(token)

    jda.disableCache(CacheFlag.ACTIVITY, CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
    jda.setChunkingFilter(ChunkingFilter.NONE)

    jda.addEventListeners(Bot)
    jda.setActivity(Activity.listening("the voices in my head"))

    jda.build()
}