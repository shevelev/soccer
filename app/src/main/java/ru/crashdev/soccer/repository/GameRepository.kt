package ru.crashdev.soccer.repository

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.local.GamesDao
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player

class GameRepository(private val gamesDao: GamesDao) {

    fun getDataGamesFromLocal(): LiveData<List<Game>> {
        return gamesDao.getGamesList()
    }

    fun saveGame(game: Game): Long {
        return gamesDao.insert(game)
    }

    fun getDataPlayersFromLocal(): LiveData<List<Player>> {
        return gamesDao.getPlayersList()
    }

    fun getDataActivePlayersFromLocal(): List<Player> {
        return gamesDao.getActivePlayersList()
    }

    fun savePlayer(player: Player) {
        gamesDao.insertPlayer(player)
    }

    fun loadGame(gameId: Long): LiveData<Game> {
        return gamesDao.loadGameById(gameId)
    }

    fun loadGame2(gameId: Long): Game {
        return gamesDao.loadGameById2(gameId)
    }

    fun updatePlayer(playerId: Long, goal: Int, missed: Int) {
        gamesDao.updatePlayer(playerId, goal, missed)
    }

    fun deletePlayer(player: Player) {
        gamesDao.deletePlayer(player)
    }

    fun deleteGame(game: Game) {
        gamesDao.deleteGame(game)
    }

    fun updateActive(playerId: Long, checked: Boolean) {
        gamesDao.updateActive(playerId, checked)
    }

    fun getProfilePlayer(playerId: Long): Player {
        return gamesDao.getProfilePlayer(playerId)
    }

    fun getGamesByPlayer(playerId: Long): List<Game> {
        return gamesDao.getGamesByPlayer(playerId)
    }
}