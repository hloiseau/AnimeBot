package io.andakawa.bot.commands.utils

import io.andakawa.bot.mal.GetAnimeList
import net.sandrohc.jikan.model.anime.Anime
import net.sandrohc.jikan.model.anime.AnimeSearchSub

fun AnimeSearchSub.ToFullAnime(): Anime = GetAnimeList().getAnimeById(this.malId)!!