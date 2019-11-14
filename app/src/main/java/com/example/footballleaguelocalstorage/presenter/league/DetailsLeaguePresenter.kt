package com.example.footballleaguelocalstorage.presenter.league

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.coroutines.CoroutineContextProvider
import com.example.footballleaguelocalstorage.interfaces.league.DetailsLeagueView
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsLeaguePresenter(private val view : DetailsLeagueView, private val apiRepository : ApiRepository, private val gson : Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getTeamList(league : String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse:: class.java)


            view.hideLoading()
            view.showTeamList(data.teams)

        }
    }
}