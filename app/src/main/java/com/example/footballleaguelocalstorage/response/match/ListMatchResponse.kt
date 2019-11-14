package com.example.footballleaguelocalstorage.response.match

import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.google.gson.annotations.SerializedName

data class ListMatchResponse(
    @SerializedName("events")
    val match: List<FootballLeagueMatch>
)