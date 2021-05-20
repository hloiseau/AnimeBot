package io.andakawa.bot.commands.utils

import io.andakawa.bot.mal.GetAnimeList
import net.sandrohc.jikan.model.anime.AnimeSearchSub

fun AnimeSearchSub.getEnglishTitle(): String = GetAnimeList().getAnimeById(this.malId)?.titleEnglish!!
