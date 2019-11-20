package com.example.footballleaguelocalstorage.adapter.team

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.model.team.TeamData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_row_league.*

class FootballTeamAdapter (private val context : Context, private val items : List<TeamData>) : RecyclerView.Adapter<FootballTeamAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items_row_teams, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items : TeamData){
            tv_name.text = items.teamName

            Glide.with(itemView.context).load(items.teamBadge).apply(RequestOptions.overrideOf(250,250)).into(img_item_photo)

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, items.teamName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}