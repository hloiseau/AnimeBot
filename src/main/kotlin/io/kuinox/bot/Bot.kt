package io.kuinox.bot

import io.kuinox.bot.commands.*
import io.kuinox.bot.commands.helpers.Command
import io.kuinox.bot.listeners.EventListener
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.JDABuilder
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import org.reflections.scanners.SubTypesScanner
import org.reflections.scanners.TypeAnnotationsScanner
import org.reflections.util.ConfigurationBuilder

class Bot {
    val commands: List<Command>

    init {
        println("Discovering commands...")
        val reflections = Reflections("io.kuinox.bot", SubTypesScanner())
        commands = reflections.getSubTypesOf(Command::class.java)
            .map { it.kotlin }
            .filter { !it.isAbstract }
            .map { it.constructors.single().call() }
    }


    fun start() {
        println("Commands available: ${commands.size}\n")
        val builder = JDABuilder.createDefault(Settings.BOT_TOKEN)
            .setAutoReconnect(true)
                .addEventListeners(EventListener(this, Store.create(Settings.STORE_LOCATION)))

        val jda = builder.build()
    }
}
