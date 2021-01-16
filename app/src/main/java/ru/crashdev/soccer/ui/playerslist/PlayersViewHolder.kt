package ru.crashdev.soccer.ui.playerslist

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.players_list_item.view.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Player

class PlayersViewHolder(itemView: View, private val viewModel: PlayersListViewModel) :
    RecyclerView.ViewHolder(itemView) {
    fun bindItem(item: Player) {
        itemView.playerId.text = item.playerId.toString()
        itemView.playerName.text = item.playerName
        itemView.playerScored.text = item.scoredBalls.toString()
        itemView.playerMissed.text = item.missedBalls.toString()
        itemView.playerActive.isChecked = item.isActive

        itemView.playerActive.setOnClickListener {
            viewModel.updateIsActive(item.playerId, itemView.playerActive.isChecked)
        }

        itemView.playerName.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("playerId", item.playerId)
            it.findNavController().navigate(R.id.profileFragment, bundle)
        }

    }
}