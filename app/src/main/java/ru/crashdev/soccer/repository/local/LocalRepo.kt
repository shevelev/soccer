package ru.crashdev.soccer.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import ru.crashdev.soccer.contract.LocalRepositoryContract
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

class LocalRepo(context: Context) : LocalRepositoryContract {

    private val localDatabase = SoccerDB.getInstance(context).gamesDao()
    private val allGames: LiveData<List<Games>>
    private val allPlayers: LiveData<List<Player>>

    init {
        allGames = localDatabase.getGamesList()
        allPlayers = localDatabase.getPlayersList()
    }

    override fun getDataGames() = allGames

    override fun saveGame(game: Games) {
        localDatabase.insert(game)
    }

    override fun updatePlayer(playerId: Long, goal: Int, missed: Int) {
        localDatabase.updatePlayer(playerId, goal, missed)
    }

    override fun getDataPlayers() = allPlayers
    override fun getDataActivePlayers() = localDatabase.getActivePlayersList()


    override fun savePlayer(player: Player) {
        localDatabase.insertPlayer(player)
    }

    override fun updateActive(playerId: Long, checked: Boolean) {
        localDatabase.updateActive(playerId, checked)
    }

    override fun deletePlayer(player: Player) {
        localDatabase.deletePlayer(player)
    }

    override fun getProfilePlayer(playerId: Long): Player {
        return localDatabase.getProfilePlayer(playerId)
    }

    override fun getGamesByPlayer(): List<Games> {
        return localDatabase.getGamesByPlayer()
    }
}