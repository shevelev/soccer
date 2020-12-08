package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

interface DataRepositoryContract {
    fun getDataGamesFromLocal(): LiveData<List<Games>>
    fun saveGame(game: Games)

    fun getDataPlayersFromLocal(): LiveData<List<Player>>
    fun getDataActivePlayersFromLocal(): LiveData<List<Player>>
    fun savePlayer(player: Player)
}