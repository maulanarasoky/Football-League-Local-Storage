package com.example.footballleaguelocalstorage.presenter.league

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.interfaces.league.DetailsLeagueView
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsLeaguePresenter(private val view : DetailsLeagueView, private val apiRepository : ApiRepository, private val gson : Gson){
    fun getTeamList(league : String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}