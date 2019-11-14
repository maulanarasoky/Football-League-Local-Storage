package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.coroutines.CoroutineContextProvider
import com.example.footballleaguelocalstorage.interfaces.league.match.LeagueMatchView
import com.example.footballleaguelocalstorage.response.match.ListMatchResponse
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListMatchPresenter(private val view : LeagueMatchView, private val apiRepository : ApiRepository, private val gson : Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getLastMatchList(idLeague : String?, nameLeague: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(idLeague)).await(),
                ListMatchResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)).await(),
                TeamResponse:: class.java)


            view.hideLoading()
            view.showTeamList(dataMatch.match, dataTeam.teams)

        }
    }

    fun getNextMatchList(idLeague : String?, nameLeague: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(idLeague)).await(),
                ListMatchResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)).await(),
                TeamResponse:: class.java)


            view.hideLoading()
            view.showTeamList(dataMatch.match, dataTeam.teams)

        }
    }
}