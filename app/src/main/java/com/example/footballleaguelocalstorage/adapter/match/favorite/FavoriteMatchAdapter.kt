package com.example.footballleaguelocalstorage.adapter.match.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.activity.match.favorite.DetailsFavoriteMatchActivity
import com.example.footballleaguelocalstorage.model.favorite.Favorite
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteMatchAdapter( private val dataFavorite: List<Favorite>
) : RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_match,
                parent,
                false
            )
        )

    override fun getItemCount() = dataFavorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.matchName.text = dataFavorite[position].eventName

        if (dataFavorite[position].scoreHomeTeam != null && dataFavorite[position].scoreAwayTeam != null) {
            holder.scoreHome.text = dataFavorite[position].scoreHomeTeam.toString()
            holder.scoreAway.text = dataFavorite[position].scoreAwayTeam.toString()
        } else {
            holder.scoreHome.text = "-"
            holder.scoreAway.text = "-"
        }

        holder.homeName.text = dataFavorite[position].nameHomeTeam
        holder.awayName.text = dataFavorite[position].nameAwayTeam

        holder.dateMatch.text = dataFavorite[position].dateEvent

        if(dataFavorite[position].homePhoto != null){
            Glide.with(holder.itemView.context).load(dataFavorite[position].homePhoto).into(holder.imgHomePhoto)
        }else{
            Glide.with(holder.itemView.context).load(R.drawable.ball).into(holder.imgHomePhoto)
        }

        if (dataFavorite[position].awayPhoto != null){
            Glide.with(holder.itemView.context).load(dataFavorite[position].awayPhoto).into(holder.imgAwayPhoto)
        }else{
            Glide.with(holder.itemView.context).load(R.drawable.ball).into(holder.imgAwayPhoto)
        }
        val imgHomePhoto = dataFavorite[position].homePhoto
        val imgAwayPhoto = dataFavorite[position].awayPhoto


        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity<DetailsFavoriteMatchActivity>(
                DetailsFavoriteMatchActivity.dataMatchParcel to dataFavorite[position],
                DetailsFavoriteMatchActivity.homePhoto to imgHomePhoto,
                DetailsFavoriteMatchActivity.awayPhoto to imgAwayPhoto,
                "status" to "Last Match"
            )
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        val matchName: TextView = containerView.find(R.id.matchName)
        val scoreHome: TextView = containerView.find(R.id.scoreHome)
        val scoreAway: TextView = containerView.find(R.id.scoreAway)
        val homeName: TextView = containerView.find(R.id.homeName)
        val awayName: TextView = containerView.find(R.id.awayName)
        val dateMatch: TextView = containerView.find(R.id.dateMatch)
        val imgHomePhoto: ImageView = containerView.find(R.id.img_home_photo)
        val imgAwayPhoto: ImageView = containerView.find(R.id.img_away_photo)
    }
}