package com.example.footballleaguelocalstorage.model.league

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueData(val id : String?, val name : String?, val description : String?, val image : Int?) : Parcelable