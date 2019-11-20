package com.example.footballleaguelocalstorage.model.team

import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("idTeam")
    var idTeam : Int? = 0,

    @SerializedName("strTeam")
    var teamName : String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge : String? = null
)