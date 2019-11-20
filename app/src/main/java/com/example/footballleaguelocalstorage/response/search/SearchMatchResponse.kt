package com.example.footballleaguelocalstorage.response.search

import com.example.footballleaguelocalstorage.model.match.Match
import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val allMatch: List<Match>? = null
)