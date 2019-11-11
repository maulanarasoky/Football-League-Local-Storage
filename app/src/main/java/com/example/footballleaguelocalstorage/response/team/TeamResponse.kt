package com.example.footballleaguelocalstorage.response.team

import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<FootballTeamData>,

    @SerializedName("events")
    val match: List<FootballLeagueMatch>,

    @SerializedName("event")
    val allMatch : List<FootballLeagueMatch>? = null
)