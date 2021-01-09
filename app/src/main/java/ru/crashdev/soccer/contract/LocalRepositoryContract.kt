package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

interface LocalRepositoryContract {
    fun getDataGames(): LiveData<List<Games>>
    fun saveGame(game: Games)
    fun updatePlayer(playerId: Long, goal: Int, missed: Int)


    fun getDataPlayers(): LiveData<List<Player>>
    fun getDataActivePlayers(): List<Player>
    fun savePlayer(player: Player)
    fun updateActive(playerId: Long, checked: Boolean)
    fun deletePlayer(player: Player)

    fun getProfilePlayer(playerId: Long): Player
    fun getGamesByPlayer(): List<Games>
}