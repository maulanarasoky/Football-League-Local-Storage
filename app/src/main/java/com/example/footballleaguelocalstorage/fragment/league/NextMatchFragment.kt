package com.example.footballleaguelocalstorage.fragment.league


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.adapter.match.FootballLeagueMatchAdapter
import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.interfaces.league.match.LeagueMatchView
import com.example.footballleaguelocalstorage.model.match.Match
import com.example.footballleaguelocalstorage.model.team.TeamData
import com.example.footballleaguelocalstorage.presenter.match.ListMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.find

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(),
    LeagueMatchView {

    companion object {
        const val idLeague = "id_league"
    }

    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: ListMatchPresenter

    private lateinit var adapter: FootballLeagueMatchAdapter

    private var dataMatch: MutableList<Match> = mutableListOf()
    private var dataTeam: MutableList<TeamData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idLeague = activity!!.intent.getStringExtra(idLeague)
        val nameLeague = activity!!.intent.getStringExtra("nameLeague")

        progressBar = find(R.id.progressBar)


        val request = ApiRepository()
        val gson = Gson()
        presenter = ListMatchPresenter(
            this,
            request,
            gson
        )
        presenter.getNextMatchList(idLeague, nameLeague)
        adapter =
            FootballLeagueMatchAdapter(
                dataMatch,
                dataTeam
            )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatchList(
        dataMatch: List<Match>?,
        dataTeam: List<TeamData>
    ) {
        this.dataMatch.clear()
        this.dataTeam.clear()
        if (dataMatch != null) {
            this.dataMatch.addAll(dataMatch)
        } else {
            textNoData.visibility = View.VISIBLE
        }
        this.dataTeam.addAll(dataTeam)
        adapter.notifyDataSetChanged()
    }


}
