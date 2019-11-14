package com.example.footballleaguelocalstorage.interfaces.league.match

import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData

interface LeagueMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(dataMatch : List<FootballLeagueMatch>?, dataTeam: List<FootballTeamData>)
}