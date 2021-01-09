package ru.crashdev.soccer.ui.mainscreen

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.games_list_item.view.*
import ru.crashdev.soccer.repository.model.Games

class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindFields(item: Games) {
        itemView.pla1.text = "${item.players.playerA1} (${item.points.point_A1})"
        itemView.pla2.text = "${item.players.playerA2} (${item.points.point_A2})"
        itemView.plb1.text = "${item.players.playerB1} (${item.points.point_B1})"
        itemView.plb2.text = "${item.players.playerB2} (${item.points.point_B2})"
        itemView.pointA.text = item.pointA.toString()
        itemView.pointB.text = item.pointB.toString()

        when (adapterPosition%2) {
            0 -> itemView.setBackgroundColor(Color.argb(45, 0, 255, 43))
            1 -> itemView.setBackgroundColor(Color.argb(45,255,255,0))
        }
    }
}
