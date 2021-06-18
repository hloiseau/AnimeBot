package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetPing : CommandWithArguments("setPing", SetPingHandler()) {

    override val helpDescription: String = "> ${Settings.PREFIX}setping <mention> → Set ping command to mention user or group"
}

class SetPingHandler : CommandHandler( true, "New ping message"){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        if(event.author.idLong == 293811468787384321 || event.author.idLong == 656522661291098143){
            event.message.reply(("Non.")).queue()
            throw Exception("Dan cannot use setping command !")
        }

        val newPing = args.single()
        val newContent = store.getContent().copy(
            pingResponse = newPing
        )
        store.updateContent(newContent)
        event.message.reply("Ping response set to: $newPing").queue()
    }
}