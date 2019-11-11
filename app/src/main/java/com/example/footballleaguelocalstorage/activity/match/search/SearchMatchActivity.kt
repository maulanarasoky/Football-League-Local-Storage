package com.example.footballleaguelocalstorage.activity.match.search

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.adapter.match.FootballLeagueMatchAdapter
import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.interfaces.match.search.SearchMatchView
import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import com.example.footballleaguelocalstorage.presenter.match.search.SearchMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.find

class SearchMatchActivity : AppCompatActivity(),
    SearchMatchView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: FootballLeagueMatchAdapter
    private val dataMatch: MutableList<FootballLeagueMatch> = mutableListOf()
    private val dataTeam: MutableList<FootballTeamData> = mutableListOf()

    companion object{
        const val searchLeagueName = "SearchName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        supportActionBar?.title = "Search for Match"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = find(R.id.progressBar)

        recyclerView = find(R.id.recyclerView)

        adapter =
            FootballLeagueMatchAdapter(
                dataMatch,
                dataTeam
            )

        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()


                val nameLeague = intent.getStringExtra(searchLeagueName)
                textNoData.visibility = View.GONE

                val request = ApiRepository()
                val gson = Gson()

                presenter =
                    SearchMatchPresenter(
                        this@SearchMatchActivity,
                        request,
                        gson
                    )

                presenter.searchMatch(query, nameLeague)

                recyclerView.adapter = adapter

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeamList(dataMatch: List<FootballLeagueMatch>?, dataTeam: List<FootballTeamData>) {
        val nameLeague: String? = intent.getStringExtra(searchLeagueName)
        this.dataMatch.clear()
        this.dataTeam.clear()
        this.dataTeam.addAll(dataTeam)
        if (dataMatch != null) {
            for (i in dataMatch.indices){
                if(dataMatch[i].strLeague.equals(nameLeague) && dataMatch[i].strSport.equals("Soccer")){
                    val data = FootballLeagueMatch(
                        dataMatch[i].idEvent,
                        dataMatch[i].eventName,
                        dataMatch[i].nameHomeTeam,
                        dataMatch[i].nameAwayTeam,
                        dataMatch[i].scoreHomeTeam,
                        dataMatch[i].scoreAwayTeam,
                        dataMatch[i].idHomeTeam,
                        dataMatch[i].idAwayTeam,
                        dataMatch[i].dateEvent,
                        dataMatch[i].strThumb,
                        dataMatch[i].strLeague,
                        dataMatch[i].strSport
                    )
                    this.dataMatch.add(data)
                }else{
                    continue
                }
            }
        }else{
            textNoData.visibility = View.VISIBLE
        }

        adapter.notifyDataSetChanged()
    }
}
