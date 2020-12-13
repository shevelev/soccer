package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Player

interface PlayersListContract {
    interface Presenter {
        fun getData(): LiveData<List<Player>>
        fun updateIsActive(playerId: Long, checked: Boolean)

        val itemCount: Int
        fun onItemClicked(pos: Int)
        fun onBindItemView(itemView: ItemView, pos: Int)
        fun deletePlayer(pos: Int)
    }
    interface View {}

    interface ItemView {
        fun bindItem(item: Player)
    }
}