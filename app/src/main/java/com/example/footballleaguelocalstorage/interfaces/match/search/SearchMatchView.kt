package com.example.footballleaguelocalstorage.interfaces.match.search

import com.example.footballleaguelocalstorage.model.match.Match
import com.example.footballleaguelocalstorage.model.team.TeamData

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(dataMatch : List<Match>?, dataTeam: List<TeamData>)
}