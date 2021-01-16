package ru.crashdev.soccer.ui.playerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Player

class PlayersListViewModel(private val repository: GameRepository) : ViewModel() {

    var items = mutableListOf<Player>()

    fun getData(): LiveData<List<Player>> {
        return repository.getDataPlayersFromLocal()
    }

    fun deletePlayer(pos: Int) {
        repository.deletePlayer(items[pos])
    }

    fun updateIsActive(playerId: Long, checked: Boolean) {
        repository.updateActive(playerId, checked)
    }
}