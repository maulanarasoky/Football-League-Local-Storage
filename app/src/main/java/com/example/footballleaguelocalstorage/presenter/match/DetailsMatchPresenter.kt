package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.interfaces.match.DetailsMatchView
import com.example.footballleaguelocalstorage.response.match.DetailsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsMatchPresenter (private val view : DetailsMatchView, private val apiRepository : ApiRepository, private val gson : Gson) {
    fun getDetailsMatch(idMatch : String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailsMatch(idMatch)),
                DetailsResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.detailsMatch)
            }
        }
    }
}