package com.example.footballleaguelocalstorage.response.match

import com.example.footballleaguelocalstorage.model.match.DetailsMatch
import com.google.gson.annotations.SerializedName

data class DetailsResponse (
    @SerializedName("events")
    val detailsMatch: List<DetailsMatch>? = null
)