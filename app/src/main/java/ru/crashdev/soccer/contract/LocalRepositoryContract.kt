package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

interface LocalRepositoryContract {
    fun getDataGames(): LiveData<List<Games>>
    fun saveGame(game: Games)

    fun getDataPlayers(): LiveData<List<Player>>
    fun getDataActivePlayers(): LiveData<List<Player>>
    fun savePlayer(player: Player)
    fun updateActive(playerId: Long, checked: Boolean)
}