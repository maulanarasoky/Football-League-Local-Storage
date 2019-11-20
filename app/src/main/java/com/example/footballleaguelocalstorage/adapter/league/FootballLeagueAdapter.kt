package com.example.footballleaguelocalstorage.adapter.league

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.activity.league.DetailsLeagueActivity
import com.example.footballleaguelocalstorage.fragment.league.LastMatchFragment
import com.example.footballleaguelocalstorage.model.league.LeagueData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_row_league.*
import org.jetbrains.anko.startActivity

class FootballLeagueAdapter(private val context : Context, private val items : List<LeagueData>) : RecyclerView.Adapter<FootballLeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items_row_league, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items : LeagueData){
            tv_name.text = items.name

            Glide.with(itemView.context).load(items.image).apply(RequestOptions.overrideOf(250,250)).into(img_item_photo)

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailsLeagueActivity>(
                    DetailsLeagueActivity.dataParcel to items,
                    LastMatchFragment.idLeague to items.id,
                    "nameLeague" to items.name
                )
            }
        }
    }
}