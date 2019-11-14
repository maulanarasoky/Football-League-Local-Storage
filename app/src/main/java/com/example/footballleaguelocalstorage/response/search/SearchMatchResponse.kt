package com.example.footballleaguelocalstorage.response.search

import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val allMatch : List<FootballLeagueMatch>? = null
)