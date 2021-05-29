package io.kuinox.bot.mal

import net.sandrohc.jikan.Jikan
import net.sandrohc.jikan.model.anime.Anime
import net.sandrohc.jikan.model.anime.AnimeSearchSub
import net.sandrohc.jikan.model.enums.AnimeStatus


class GetAnimeList {
    private val jikan = Jikan()

    fun searchForAiringAnime(name: String): List<AnimeSearchSub>? = jikan.query()
        .anime()
        .search()
        .limit(5)
        .query(name)
        .status(AnimeStatus.AIRING)
        .execute()
        .collectList()
        .block()

    fun searchForAnime(name: String): List<AnimeSearchSub>? = jikan.query()
        .anime()
        .search()
        .limit(5)
        .query(name)
        .execute()
        .collectList()
        .block()

    fun getAnimeById(id: Int): Anime? = jikan.query().anime()[id]
        .execute()
        .block()
}