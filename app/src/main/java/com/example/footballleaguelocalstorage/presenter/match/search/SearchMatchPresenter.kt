package com.example.footballleaguelocalstorage.presenter.match.search

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.coroutines.CoroutineContextProvider
import com.example.footballleaguelocalstorage.interfaces.match.search.SearchMatchView
import com.example.footballleaguelocalstorage.response.search.SearchMatchResponse
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter(private val view : SearchMatchView, private val apiRepository : ApiRepository, private val gson : Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun searchMatch(matchName : String?, nameLeague: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchMatch(matchName)).await(),
                SearchMatchResponse:: class.java)

            val dataTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(nameLeague)).await(),
                TeamResponse:: class.java)


            view.hideLoading()
            view.showTeamList(dataMatch.allMatch, dataTeam.teams)

        }
    }
}