package io.kuinox.bot.commands

import io.kuinox.bot.mal.GetAnimeList
import net.sandrohc.jikan.model.anime.Anime
import net.sandrohc.jikan.model.anime.AnimeSearchSub

fun AnimeSearchSub.toFullAnime(): Anime = GetAnimeList().getAnimeById(this.malId)!!