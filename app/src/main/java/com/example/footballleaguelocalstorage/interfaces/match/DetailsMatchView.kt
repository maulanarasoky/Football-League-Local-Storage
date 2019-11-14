package com.example.footballleaguelocalstorage.interfaces.match

import com.example.footballleaguelocalstorage.model.match.DetailsMatch

interface DetailsMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailsMatch(detailsMatch : List<DetailsMatch>?)
}