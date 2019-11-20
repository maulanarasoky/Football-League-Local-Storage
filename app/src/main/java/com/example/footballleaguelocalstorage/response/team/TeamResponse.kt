package com.example.footballleaguelocalstorage.response.team

import com.example.footballleaguelocalstorage.model.team.TeamData
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<TeamData>
)