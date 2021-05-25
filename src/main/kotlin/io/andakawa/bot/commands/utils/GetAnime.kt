package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.commands.CommandHandler
import io.andakawa.bot.commands.CommandWithArguments
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnime : CommandWithArguments("getAnime", arrayOf(GetAnimeHandler())) {

    override val helpDescription: String = "> ${Settings.PREFIX}getAnime <id> → Show anime with <id>"
}

class GetAnimeHandler : CommandHandler(true, arrayOf("Search string.")) {
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val id = args.single().filter{ it.isDigit() }.toInt()
        val anime = animeList.getAnimeById(id)
        val message = StringBuilder()
        message.append("${anime?.titleEnglish!!} \n")
        message.append("${anime.imageUrl!!} \n")
        event.channel.sendMessage(message).queue()
    }
}