package com.example.footballleaguelocalstorage.presenter.match.search

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.interfaces.match.search.SearchMatchView
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter(private val view : SearchMatchView, private val apiRepository : ApiRepository, private val gson : Gson) {
    fun searchMatch(matchName : String?, nameLeague: String?){
        view.showLoading()
        doAsync {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchMatch(matchName)),
                TeamResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)),
                TeamResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(dataMatch.allMatch, dataTeam.teams)
            }
        }
    }
}