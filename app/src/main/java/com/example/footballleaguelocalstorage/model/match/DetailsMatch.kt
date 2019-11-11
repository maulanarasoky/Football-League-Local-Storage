package com.example.footballleaguelocalstorage.model.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsMatch (
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strEvent")
    var eventName : String? = null,

    @SerializedName("dateEvent")
    var dateEvent : String? = null,

    @SerializedName("strHomeTeam")
    var homeTeamName : String? = null,

    @SerializedName("strAwayTeam")
    var awayTeamName : String? = null,

    @SerializedName("intHomeScore")
    var homeScore : Int? = 0,

    @SerializedName("intAwayScore")
    var awayScore : Int? = 0,

    @SerializedName("strHomeFormation")
    var homeFormation : String? = null,

    @SerializedName("strAwayFormation")
    var awayFormation : String? = null

) : Parcelable