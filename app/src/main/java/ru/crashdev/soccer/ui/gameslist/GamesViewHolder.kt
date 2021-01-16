package ru.crashdev.soccer.ui.gameslist

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.games_list_item.view.*
import ru.crashdev.soccer.repository.model.Game

class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindFields(item: Game) {
        itemView.pla1.text = "${item.player1Name} (${item.player1Point})"
        itemView.pla2.text = "${item.player2Name} (${item.player2Point})"
        itemView.plb1.text = "${item.player3Name} (${item.player3Point})"
        itemView.plb2.text = "${item.player4Name} (${item.player4Point})"
        itemView.pointA.text = item.pointA.toString()
        itemView.pointB.text = item.pointB.toString()

        when (adapterPosition%2) {
            0 -> itemView.setBackgroundColor(Color.argb(45, 0, 255, 43))
            1 -> itemView.setBackgroundColor(Color.argb(45,255,255,0))
        }
    }
}
