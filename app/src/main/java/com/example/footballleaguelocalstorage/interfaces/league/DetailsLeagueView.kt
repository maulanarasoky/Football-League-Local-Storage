package com.example.footballleaguelocalstorage.interfaces.league

import com.example.footballleaguelocalstorage.model.team.TeamData

interface DetailsLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<TeamData>)
}