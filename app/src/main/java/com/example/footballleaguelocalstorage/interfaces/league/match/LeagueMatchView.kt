package com.example.footballleaguelocalstorage.interfaces.league.match

import com.example.footballleaguelocalstorage.model.match.Match
import com.example.footballleaguelocalstorage.model.team.TeamData

interface LeagueMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(dataMatch : List<Match>?, dataTeam: List<TeamData>)
}