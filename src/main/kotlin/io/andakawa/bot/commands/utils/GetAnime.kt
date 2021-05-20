package io.andakawa.bot.commands.utils

import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.mal.GetAnimeList
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class GetAnime : Command("${Settings.PREFIX}getAnime") {
    override fun run(event: GuildMessageReceivedEvent) {
        val anime = GetAnimeList()
        val id = event.message.contentRaw.filter{ it.isDigit() }.toInt()
        event.channel.sendMessage(anime.getAnimeById(id)?.titleEnglish!!).queue()
    }
}