package ru.crashdev.soccer.ui.gameslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Game

class GamesListViewModel(private val repository: GameRepository) : ViewModel() {
    fun getData(): LiveData<List<Game>> {
        return repository.getDataGamesFromLocal()
    }
}