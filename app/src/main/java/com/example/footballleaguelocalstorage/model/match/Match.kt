package com.example.footballleaguelocalstorage.model.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strEvent")
    var eventName: String? = null,

    @SerializedName("strHomeTeam")
    var nameHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var nameAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var scoreHomeTeam: Int? = 0,

    @SerializedName("intAwayScore")
    var scoreAwayTeam: Int? = 0,

    @SerializedName("idHomeTeam")
    var idHomeTeam: Int? = 0,

    @SerializedName("idAwayTeam")
    var idAwayTeam: Int? = 0,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strThumb")
    var strThumb: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null

) : Parcelable