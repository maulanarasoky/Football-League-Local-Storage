package com.example.footballleaguelocalstorage.activity.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.activity.match.search.SearchMatchActivity
import com.example.footballleaguelocalstorage.adapter.team.FootballTeamAdapter
import com.example.footballleaguelocalstorage.adapter.viewpager.ViewPagerAdapter
import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.fragment.league.LastMatchFragment
import com.example.footballleaguelocalstorage.fragment.league.NextMatchFragment
import com.example.footballleaguelocalstorage.interfaces.league.DetailsLeagueView
import com.example.footballleaguelocalstorage.model.league.LeagueData
import com.example.footballleaguelocalstorage.model.team.TeamData
import com.example.footballleaguelocalstorage.presenter.league.DetailsLeaguePresenter
import com.github.ybq.android.spinkit.style.Wave
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details_league.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DetailsLeagueActivity : AppCompatActivity(),
    DetailsLeagueView {

    companion object {
        const val dataParcel = "data_parcel"
    }

    private val lastMatchFragment : LastMatchFragment =
        LastMatchFragment()
    private val nextMatchFragment : NextMatchFragment =
        NextMatchFragment()

    private lateinit var presenter : DetailsLeaguePresenter

    private lateinit var progressBar : ProgressBar

    private lateinit var adapter : FootballTeamAdapter

    private var items : MutableList<TeamData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_league)

        val dataLeague: LeagueData? = intent.getParcelableExtra(dataParcel)

        val collapsingToolbar: CollapsingToolbarLayout = find(R.id.collapsingToolbar)
        collapsingToolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

        val toolbar: Toolbar = find(R.id.toolBar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = dataLeague?.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = findViewById(R.id.progressBar)

        val viewPagerAdapter =
            ViewPagerAdapter(
                supportFragmentManager
            )
        viewPagerAdapter.addFragment(lastMatchFragment, "Last Match")
        viewPagerAdapter.addFragment(nextMatchFragment, "Next Match")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val styleProgressBar = Wave()
        progressBar.indeterminateDrawable = styleProgressBar

        Glide.with(this).load(dataLeague?.image).apply(RequestOptions.overrideOf(250, 250))
            .into(img_item_photo)
        leagueName.text = dataLeague?.name
        leagueDescription.text = dataLeague?.description

        adapter = FootballTeamAdapter(
            applicationContext,
            items
        )

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailsLeaguePresenter(
            this,
            request,
            gson
        )

        presenter.getTeamList(dataLeague?.name)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_league, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.search -> {
                val nameLeague: String? = intent.getStringExtra("nameLeague")
                startActivity<SearchMatchActivity>(
                    SearchMatchActivity.searchLeagueName to nameLeague
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<TeamData>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
