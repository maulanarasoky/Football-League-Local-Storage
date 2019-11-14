package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.api.TheSportDBApi
import com.example.footballleaguelocalstorage.coroutines.CoroutineContextProvider
import com.example.footballleaguelocalstorage.interfaces.match.DetailsMatchView
import com.example.footballleaguelocalstorage.response.match.DetailsResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsMatchPresenter (private val view : DetailsMatchView, private val apiRepository : ApiRepository, private val gson : Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getDetailsMatch(idMatch : String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailsMatch(idMatch)).await(),
                DetailsResponse:: class.java)

            view.hideLoading()
            view.showTeamList(data.detailsMatch)

        }
    }
}