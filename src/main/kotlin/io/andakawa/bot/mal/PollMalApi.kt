package io.andakawa.bot.mal

import kotlinx.coroutines.*
import net.dv8tion.jda.api.JDA


class PollMalApi(val jda: JDA, val animeList: GetAnimeList) {

    fun getAnimeUpdates(animeId: Int, channelId: Long) = CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
        update(animeId, channelId)
    }

    private suspend fun update(animeId: Int, channelId: Long) = coroutineScope {
        launch {
            while (true) {
                val anime = animeList.getAnimeById(animeId)?.titleEnglish
                jda.getTextChannelById(channelId)?.sendMessage(anime!!)?.queue()
                delay(1000 * 60 * 10)
            }
        }
    }
}


