package com.example.footballleaguelocalstorage.fragment.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.adapter.match.favorite.FavoriteMatchAdapter
import com.example.footballleaguelocalstorage.database.database
import com.example.footballleaguelocalstorage.model.favorite.Favorite
import kotlinx.android.synthetic.main.fragment_favorite_last_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

/**
 * A simple [Fragment] subclass.
 */
class FavoriteLastMatchFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_last_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listMatch = find(R.id.recyclerView)
        progressBar = find(R.id.progressBar)

        adapter =
            FavoriteMatchAdapter(
                favorites
            )

        listMatch.layoutManager = LinearLayoutManager(ctx)
        listMatch.adapter = adapter

        showFavorite()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(STATUS = {status})", "status" to "Last Match")
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()){
                favorites.addAll(favorite)
            }else{
                textNoData.visibility = View.VISIBLE
            }
            adapter.notifyDataSetChanged()
        }
    }

}
