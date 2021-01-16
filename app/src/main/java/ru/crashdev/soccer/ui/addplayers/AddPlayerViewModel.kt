package ru.crashdev.soccer.ui.addplayers

import androidx.lifecycle.ViewModel
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Player

class AddPlayerViewModel(private val repository: GameRepository) : ViewModel() {

    private lateinit var player: Player
    private var name = ""

    fun savePlayer() {
        repository.savePlayer(Player(name, 0, 0, true))
    }

    fun updateName(name: String) {
        this.name = name
    }
}