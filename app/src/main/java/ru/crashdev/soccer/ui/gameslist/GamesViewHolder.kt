package ru.crashdev.soccer.ui.gameslist

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.databinding.GamesListItemBinding
import ru.crashdev.soccer.repository.model.Game

class GamesViewHolder(binding: GamesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val player1 = binding.pla1
    private val player2 = binding.pla2
    private val player3 = binding.plb1
    private val player4 = binding.plb2
    private val pointA = binding.pointA
    private val pointB = binding.pointB

    fun bindFields(item: Game) {
        player1.text = "${item.player1Name} (${item.player1Point})"
        player2.text = "${item.player2Name} (${item.player2Point})"
        player3.text = "${item.player3Name} (${item.player3Point})"
        player4.text = "${item.player4Name} (${item.player4Point})"
        pointA.text = item.pointA.toString()
        pointB.text = item.pointB.toString()

        when (adapterPosition % 2) {
            0 -> itemView.setBackgroundColor(Color.argb(45, 0, 255, 43))
            1 -> itemView.setBackgroundColor(Color.argb(45, 255, 255, 0))
        }
    }
}