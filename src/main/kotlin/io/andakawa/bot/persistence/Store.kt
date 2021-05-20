package io.andakawa.bot.persistence

import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path

class Store private constructor(private val contentPath:Path) {
    private var content: StoreContent? = null
    private lateinit var job: Job
    companion object {
        suspend fun create(storePath: Path) :Store {
            val store = Store(storePath);
            store.job = CoroutineScope(Dispatchers.Default).launch {
                val txt = File(storePath.toString()).bufferedReader()
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