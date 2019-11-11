package com.example.footballleaguelocalstorage.model.favorite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Favorite(
    val id: Long?,
    val idEvent: String?,
    val eventName: String?,
    val homePhoto: String?,
    val awayPhoto: String?,
    val nameHomeTeam: String?,
    val nameAwayTeam: String?,
    val scoreHomeTeam: String?,
    val scoreAwayTeam: String?,
    val dateEvent: String?,
    val strThumb: String?,
    val status: String?
): Parcelable {
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val HOME_PHOTO: String = "HOME_PHOTO"
        const val AWAY_PHOTO: String = "AWAY_PHOTO"
        const val NAME_HOME_TEAM: String = "NAME_HOME_TEAM"
        const val NAME_AWAY_TEAM: String = "NAME_AWAY_TEAM"
        const val SCORE_HOME_TEAM: String = "SCORE_HOME_TEAM"
        const val SCORE_AWAY_TEAM: String = "SCORE_AWAY_TEAM"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val STR_THUMB: String = "STR_THUMB"
        const val STATUS: String = "STATUS"
    }
}