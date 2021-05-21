package io.andakawa.bot.persistence

import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path

class Store private constructor(private val contentPath:String) {
    private var content: StoreContent? = null
    private lateinit var job: Job
    companion object {
        fun create(storePath: String) :Store {
            val store = Store(storePath);
            store.job = CoroutineScope(Dispatchers.Default).launch {
                val txt = File(storePath).bufferedReader()
                    .use{ it.readText()}
                store.content = Json.decodeFromString<StoreContent>(txt);
            }
            return store;
        }
    }


    suspend fun updateContent(newContent: StoreContent) {
        job.join();
        content = newContent;
    }
    suspend fun getContent(): StoreContent {
        job.join();
        return content!!;
    }
}