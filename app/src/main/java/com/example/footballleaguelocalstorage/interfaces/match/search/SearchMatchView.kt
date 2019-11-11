package com.example.footballleaguelocalstorage.interfaces.match.search

import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(dataMatch : List<FootballLeagueMatch>?, dataTeam: List<FootballTeamData>)
}