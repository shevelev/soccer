package ru.crashdev.soccer.ui.playerslist

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.databinding.PlayersListItemBinding
import ru.crashdev.soccer.repository.model.Player

class PlayersViewHolder(binding: PlayersListItemBinding, private val viewModel: PlayersListViewModel) : RecyclerView.ViewHolder(binding.root) {

    private val playerId = binding.playerId
    private val playerName = binding.playerName
    private val playerScored = binding.playerScored
    private val playerMissed = binding.playerMissed
    private val playerActive = binding.playerActive

    fun bindItem(item: Player) {

        playerId.text = item.playerId.toString()
        playerName.text = item.playerName
        playerScored.text = item.scoredBalls.toString()
        playerMissed.text = item.missedBalls.toString()
        playerActive.isChecked = item.isActive

        playerActive.setOnClickListener {
            viewModel.updateIsActive(item.playerId, playerActive.isChecked)
        }

        playerName.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("playerId", item.playerId)
            it.findNavController().navigate(R.id.profileFragment, bundle)
        }

    }
}