package com.example.footballleaguelocalstorage.api

import android.net.Uri
import com.example.footballleaguelocalstorage.BuildConfig

object TheSportDBApi {
    fun getTeams(league : String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }

    fun getLastMatch(idLeague : String?):String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=${idLeague}"
    }

    fun getNextMatch(idLeague : String?):String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=${idLeague}"
    }

    fun getDetailsMatch(idMatch : String?) : String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=${idMatch}"
    }

    fun searchMatch(matchName : String?) : String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=${matchName}"
    }
}