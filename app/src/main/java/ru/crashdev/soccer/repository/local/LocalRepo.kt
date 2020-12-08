package ru.crashdev.soccer.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import ru.crashdev.soccer.contract.LocalRepositoryContract
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.ui.gamescreen.GamePlayPresenter

class LocalRepo(context: Context) : LocalRepositoryContract {

    private val localDatabase = SoccerDB.getInstance(context).gamesDao()
    private val allGames: LiveData<List<Games>>
    private val allPlayers: LiveData<List<Player>>
    private val allActivePlayers: LiveData<List<Player>>

    init {
        allGames = localDatabase.getGamesList()
        allPlayers = localDatabase.getPlayersList()
        allActivePlayers = localDatabase.getActivePlayersList()
    }

    override fun getDataGames() = allGames

    override fun saveGame(game: Games) {
        localDatabase.insert(game)
    }

    override fun getDataPlayers() = allPlayers
    override fun getDataActivePlayers() = allActivePlayers


    override fun savePlayer(player: Player) {
        localDatabase.insertPlayer(player)
    }

    override fun updateActive(playerId: Long, checked: Boolean) {
        localDatabase.updateActive(playerId, checked)
    }
}