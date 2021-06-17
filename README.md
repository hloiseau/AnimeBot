# Discord Kotlin AnimeBot

This Bot is written in Kotlin using [JDA](https://github.com/DV8FromTheWorld/JDA).

### Important

You need to [Create a new App on Discord](https://discordapp.com/developers/applications/me/create) and set it up as _Bot User_. Retrieve your token and paste it in the Settings.kt. The token is used to authenticate your Bot to the Discord Network otherwise it won't connect to any server.

### Installation

Create a Settings.kt object
```Kotlin
package io.kuinox.bot

object Settings {
    // Insert your token here
    val BOT_TOKEN = ""

    // You may edit this in order to prevent multiple bots from responding to the same command
    val PREFIX = "?"

    //Insert the path to save the data for the bot
    val STORE_LOCATION = ""
}
```
