package ru.crashdev.soccer.ui.profile

import androidx.lifecycle.ViewModel
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.repository.model.Profile

class ProfileViewModel(private val repository: GameRepository) : ViewModel() {

    private lateinit var player: Player
    private lateinit var games: List<Game>
    private var countGame = 0
    private var missedGame = 0

    lateinit var profile: Profile

    fun loadData(playerId: Long) {
        player = repository.getProfilePlayer(playerId)
        games = repository.getGamesByPlayer(playerId)

        countGame = games.size
        missedGame = games.filter {
            it.player1Id.equals(player.playerId) or it.player4Id.equals(player.playerId)
        }.size

        profile = Profile(player, games, countGame, missedGame)
    }
}