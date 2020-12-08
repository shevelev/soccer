package ru.crashdev.soccer.ui.playersscreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.players_list_item.view.*
import ru.crashdev.soccer.contract.PlayersListContract
import ru.crashdev.soccer.repository.model.Player

class PlayersViewHolder(private val presenter: PlayersListPresenter, itemView: View) : RecyclerView.ViewHolder(itemView),
PlayersListContract.ItemView {

//    init {
//        itemView.setOnClickListener {
//            // What to do here, we only have the item's position? Call the presenter.
//            presenter.onItemClicked(adapterPosition)
//        }
//    }

    override fun bindItem(item: Player) {
        itemView.playerId.text = item.playerId.toString()
        itemView.playerName.text = item.playerName
        itemView.playerScored.text = item.scoredBalls.toString()
        itemView.playerMissed.text = item.missedBalls.toString()
        itemView.playerActive.isChecked = item.isActive

        itemView.playerActive.setOnClickListener {
            presenter.updateIsActive(item.playerId, itemView.playerActive.isChecked)
        }

    }
}