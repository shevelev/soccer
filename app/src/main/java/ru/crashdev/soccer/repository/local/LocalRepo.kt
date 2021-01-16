//package ru.crashdev.soccer.repository.local
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import ru.crashdev.soccer.contract.LocalRepositoryContract
//import ru.crashdev.soccer.repository.model.Games
//import ru.crashdev.soccer.repository.model.Player
//
//class LocalRepo() : LocalRepositoryContract {
//
//    //private val gamesDao = SoccerDB.getInstance(context).gamesDao()
//    private val allGames: LiveData<List<Games>>
//    private val allPlayers: LiveData<List<Player>>
//
//    init {
//        allGames = gamesDao.getGamesList()
//        allPlayers = gamesDao.getPlayersList()
//    }
//
//    override fun getDataGames() = allGames
//
//    override fun saveGame(game: Games) {
//        gamesDao.insert(game)
//    }
//
//    override fun updatePlayer(playerId: Long, goal: Int, missed: Int) {
//        gamesDao.updatePlayer(playerId, goal, missed)
//    }
//
//    override fun getDataPlayers() = allPlayers
//    override fun getDataActivePlayers() = gamesDao.getActivePlayersList()
//
//
//    override fun savePlayer(player: Player) {
//        gamesDao.insertPlayer(player)
//    }
//
//    override fun updateActive(playerId: Long, checked: Boolean) {
//        gamesDao.updateActive(playerId, checked)
//    }
//
//    override fun deletePlayer(player: Player) {
//        gamesDao.deletePlayer(player)
//    }
//
//    override fun getProfilePlayer(playerId: Long): Player {
//        return gamesDao.getProfilePlayer(playerId)
//    }
//
//    override fun getGamesByPlayer(): List<Games> {
//        return gamesDao.getGamesByPlayer()
//    }
//}