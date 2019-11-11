package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.interfaces.league.match.LeagueMatchView
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListMatchPresenter(private val view : LeagueMatchView, private val apiRepository : ApiRepository, private val gson : Gson) {
    fun getLastMatchList(idLeague : String?, nameLeague: String?) {
        view.showLoading()
        doAsync {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(idLeague)),
                TeamResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)),
                TeamResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(dataMatch.match, dataTeam.teams)
            }
        }
    }

    fun getNextMatchList(idLeague : String?, nameLeague: String?) {
        view.showLoading()
        doAsync {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(idLeague)),
                TeamResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)),
                TeamResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(dataMatch.match, dataTeam.teams)
            }
        }
    }
}