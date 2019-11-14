package com.example.footballleaguelocalstorage.activity.match.favorite

import android.content.res.ColorStateList
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.database.database
import com.example.footballleaguelocalstorage.interfaces.match.DetailsMatchView
import com.example.footballleaguelocalstorage.model.match.DetailsMatch
import com.example.footballleaguelocalstorage.model.favorite.Favorite
import com.example.footballleaguelocalstorage.presenter.match.DetailsMatchPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find

class DetailsFavoriteMatchActivity : AppCompatActivity(),
    DetailsMatchView {

    companion object{
        const val dataMatchParcel = "dataMatchParcel"
        const val homePhoto = "homePhoto"
        const val awayPhoto = "awayPhoto"
    }

    private var isFavorite: Boolean = false

    private var dataDetailsMatch : MutableList<DetailsMatch> = mutableListOf()

    private lateinit var match : DetailsMatch

    private lateinit var progressBar : ProgressBar
    private lateinit var presenter : DetailsMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_favorite_match)

        val dataMatch: Favorite? = intent.getParcelableExtra(dataMatchParcel)

        val toolbar: Toolbar = find(R.id.toolBar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = dataMatch?.eventName

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = find(R.id.progressBar)


        favoriteState()
        setFavorite()
        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailsMatchPresenter(
            this,
            request,
            gson
        )

        presenter.getDetailsMatch(dataMatch?.idEvent)

        if(dataMatch?.strThumb != null){
            Glide.with(this).load(dataMatch.strThumb).apply(RequestOptions.overrideOf(500,500)).into(imgHeader)

        }else{
            Glide.with(this).load(R.drawable.ball_header_black).apply(RequestOptions.overrideOf(500,500)).into(imgHeader)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite(){
        val dataMatch: Favorite? = intent.getParcelableExtra(dataMatchParcel)
        val homePhoto = intent.getStringExtra(homePhoto)
        val awayPhoto = intent.getStringExtra(awayPhoto)
        val status: String? = intent.getStringExtra("status")
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID_EVENT to match.idEvent,
                    Favorite.EVENT_NAME to match.eventName,
                    Favorite.HOME_PHOTO to homePhoto,
                    Favorite.AWAY_PHOTO to awayPhoto,
                    Favorite.NAME_HOME_TEAM to match.homeTeamName,
                    Favorite.NAME_AWAY_TEAM to match.awayTeamName,
                    Favorite.SCORE_HOME_TEAM to match.homeScore,
                    Favorite.SCORE_AWAY_TEAM to match.awayScore,
                    Favorite.DATE_EVENT to match.dateEvent,
                    Favorite.STR_THUMB to dataMatch?.strThumb,
                    Favorite.STATUS to status)
            }
            progressBar.snackbar("Added to Favorite").show()
        }catch (e: SQLiteConstraintException){
            progressBar.snackbar("error").show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(id_EVENT = {id})",
                    "id" to match.idEvent.toString())
            }
            progressBar.snackbar("Remove From Favorite").show()
        }catch (e: SQLiteConstraintException){
            progressBar.snackbar("Error").show()

        }
    }

    private fun favoriteState(){
        val dataMatch: Favorite? = intent.getParcelableExtra(dataMatchParcel)
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(ID_EVENT = {id})", "id" to dataMatch?.idEvent.toString())
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun setFavorite(){
        if (isFavorite){
            fabFavorite.setImageResource(R.drawable.ic_added_favorite)
            fabFavorite.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }else{
            fabFavorite.setImageResource(R.drawable.ic_add_favorite)
            fabFavorite.backgroundTintList = ColorStateList.valueOf(Color.RED)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showDetailsMatch(detailsMatch: List<DetailsMatch>?) {
        dataDetailsMatch.clear()
        if (detailsMatch != null) {
            dataDetailsMatch.addAll(detailsMatch)

            match = DetailsMatch(
                dataDetailsMatch[0].idEvent,
                dataDetailsMatch[0].eventName,
                dataDetailsMatch[0].dateEvent,
                dataDetailsMatch[0].homeTeamName,
                dataDetailsMatch[0].awayTeamName,
                dataDetailsMatch[0].homeScore,
                dataDetailsMatch[0].awayScore,
                dataDetailsMatch[0].homeFormation,
                dataDetailsMatch[0].awayFormation
            )
            matchName.text = match.eventName
            dateMatch.text = match.dateEvent

            homeName.text = match.homeTeamName
            awayName.text = match.awayTeamName
            if (match.homeScore != null && match.awayScore != null) {
                scoreHome.text = match.homeScore.toString()
                scoreAway.text = match.awayScore.toString()
            } else {
                scoreHome.text = "0"
                scoreAway.text = "0"
            }
            if (match.homeFormation == null) {
                homeFormation.text = "-"
            } else {
                homeFormation.text = match.homeFormation
            }
            if (match.awayFormation == null) {
                awayFormation.text = "-"
            } else {
                awayFormation.text = match.awayFormation
            }
            imgHeader.visibility = View.VISIBLE

            textTeamName.visibility = View.VISIBLE
            textScore.visibility = View.VISIBLE
            textFormation.visibility = View.VISIBLE

            val fabFavorite: FloatingActionButton = find(R.id.fabFavorite)
            fabFavorite.setOnClickListener {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
            }
        }
    }
}
