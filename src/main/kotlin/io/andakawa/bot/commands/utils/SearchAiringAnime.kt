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

class SearchAiringAnime: CommandWithArguments("airingAnime", arrayOf(SearchAiringAnimeHandler())) {

    override val helpDescription: String = "> ${Settings.PREFIX}airingAnime <name> → Show airing anime containing <name>"
}

class SearchAiringAnimeHandler : CommandHandler(true, arrayOf("Search string.")){
    override suspend fun run(args:List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = args.single()
        val message = StringBuilder()
        for ( anime in animeList.searchForAiringAnime(search)!!){
            message.append(anime.ToFullAnime().titleEnglish).append("\n")
            message.append("${anime.imageUrl!!} \n")
        }
        event.channel.sendMessage(message).queue()
    }
}