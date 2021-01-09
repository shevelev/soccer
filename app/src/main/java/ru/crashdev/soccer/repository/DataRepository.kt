package ru.crashdev.soccer.repository

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.contract.DataRepositoryContract
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

class DataRepository(val localRepo: LocalRepo) : DataRepositoryContract {
    override fun getDataGamesFromLocal(): LiveData<List<Games>> {
        return localRepo.getDataGames()
    }

    override fun saveGame(game: Games) {
        localRepo.saveGame(game)
    }

    override fun getDataPlayersFromLocal(): LiveData<List<Player>> {
        return localRepo.getDataPlayers()
    }

    override fun getDataActivePlayersFromLocal(): List<Player> {
        return localRepo.getDataActivePlayers()
    }

    override fun savePlayer(player: Player) {
        localRepo.savePlayer(player)
    }
}