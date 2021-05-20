package io.andakawa.bot

import io.andakawa.bot.mal.GetAnimeList

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        val bot = Bot(Settings.BOT_TOKEN)
        bot.start()
    }
}