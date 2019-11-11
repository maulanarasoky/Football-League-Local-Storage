package com.example.footballleaguelocalstorage.adapter.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.activity.match.DetailsMatchActivity
import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class FootballLeagueMatchAdapter(
    private val dataMatch: List<FootballLeagueMatch>,
    private val dataTeam: List<FootballTeamData>
) : RecyclerView.Adapter<FootballLeagueMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_match,
                parent,
                false
            )
        )

    override fun getItemCount() = dataMatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.matchName.text = dataMatch[position].eventName

        if(dataMatch[position].scoreHomeTeam != null && dataMatch[position].scoreAwayTeam != null){
            holder.scoreHome.text = dataMatch[position].scoreHomeTeam.toString()
            holder.scoreAway.text = dataMatch[position].scoreAwayTeam.toString()
        }else{
            holder.scoreHome.text = "-"
            holder.scoreAway.text = "-"
        }

        holder.homeName.text = dataMatch[position].nameHomeTeam
        holder.awayName.text = dataMatch[position].nameAwayTeam

        holder.dateMatch.text = dataMatch[position].dateEvent

        var imgHomePhoto = ""
        var imgAwayPhoto = ""

        for (i in dataTeam.indices){
            if (dataMatch[position].idHomeTeam == dataTeam[i].idTeam){
                Glide.with(holder.itemView.context).load(dataTeam[i].teamBadge).into(holder.imgHomePhoto)
                imgHomePhoto = dataTeam[i].teamBadge.toString()
            }
            if(dataMatch[position].idAwayTeam == dataTeam[i].idTeam){
                Glide.with(holder.itemView.context).load(dataTeam[i].teamBadge).into(holder.imgAwayPhoto)
                imgAwayPhoto = dataTeam[i].teamBadge.toString()
            }
        }

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        val todayDate: String = formatter.format(date)

        var status = ""

        if(dataMatch[position].dateEvent?.compareTo(todayDate)!! < 0 || dataMatch[position].dateEvent?.compareTo(todayDate) == 0){
            status = "Last Match"
        }else if (dataMatch[position].dateEvent?.compareTo(todayDate)!! > 0){
            status = "Next Match"
        }


        holder.itemView.setOnClickListener{
            holder.itemView.context.startActivity<DetailsMatchActivity>(
                DetailsMatchActivity.dataMatchParcel to dataMatch[position],
                DetailsMatchActivity.homePhoto to imgHomePhoto,
                DetailsMatchActivity.awayPhoto to imgAwayPhoto,
                "status" to status
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