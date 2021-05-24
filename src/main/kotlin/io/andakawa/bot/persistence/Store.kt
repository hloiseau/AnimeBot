package io.andakawa.bot.persistence

import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path

class Store private constructor(private val contentPath: String) {
    private var content: StoreContent? = null
    private lateinit var job: Job

    companion object {
        fun create(storePath: String): Store {
            val store = Store(storePath);
            if (File(storePath).exists()) {
                store.job = CoroutineScope(Dispatchers.Default).launch {
                    val txt = File(storePath).bufferedReader()
                        .use { it.readText() }
                    store.content = Json.decodeFromString<StoreContent>(txt);
                }
            } else {
                store.job = runBlocking { launch {  } }; // empty finished job ? I don't know how to do this.
                store.content = StoreContent("Pong :ping_pong:", mapOf())
            }
            return store;
        }
    }


    suspend fun updateContent(newContent: StoreContent) {
        job.join();
        content = newContent;
        job = coroutineScope{
            launch {
                val str = Json.encodeToString(newContent)
                File(contentPath).writeText(str)
            }

        }
    }

    suspend fun getContent(): StoreContent {
        job.join();
        return content!!;
    }
}