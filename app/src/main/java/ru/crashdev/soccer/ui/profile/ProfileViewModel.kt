package ru.crashdev.soccer.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import ru.crashdev.soccer.databinding.FragmentProfileBinding
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.repository.model.Profile
import ru.crashdev.soccer.ui.gameslist.GamesListAdapter

class ProfileViewModel(private val repository: GameRepository) : ViewModel() {

    private lateinit var player: Player
    private lateinit var games: List<Game>
    private var countGame = 0
    private var missedGame = 0

    lateinit var profile: Profile

    private lateinit var binding: FragmentProfileBinding

    var gamesListAdapter: GamesListAdapter = GamesListAdapter()

    fun setBinder(binder: FragmentProfileBinding, playerId: Long) {
        this.binding = binder
        init(playerId)
    }

    private fun init(playerId: Long) {
        loadData(playerId)
        showAll(profile)

        binding.recyclerViewGames.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            adapter = gamesListAdapter
        }
    }

    private fun showAll(profile: Profile) {
        showPlayer(profile.player)
        showScorredAndMissed(profile.countGame, profile.missedGame, profile.games)

        val sc = (profile.player.scoredBalls.toDouble() / (profile.countGame * 10)) * 100
        val mi = 100 - ((profile.player.missedBalls.toDouble() * 100) / (profile.missedGame * 10))

        showProgressBars(sc, mi)
    }

    fun showPlayer(player: Player) {
        binding.playerName.text = player.playerName
        binding.playerScored.text = player.scoredBalls.toString()
        binding.playerMissed.text = player.missedBalls.toString()
    }

    private fun showScorredAndMissed(allGames: Int, missedGames: Int, games: List<Game>) {
        binding.allGame.text = allGames.toString()
        this.gamesListAdapter.loadItemList(games)
    }

    private fun showProgressBars(scored: Double, missed: Double) {

        Log.d("qwe", "scored -> $scored, missed -> $missed")

        binding.progressScored.progress = scored.toInt()
        binding.progressScoredText.text = "$scored%"

        binding.progressDef.progress = missed.toInt()
        binding.progressDefText.text = "$missed%"
    }

    private fun loadData(playerId: Long) {
        player = repository.getProfilePlayer(playerId)
        games = repository.getGamesByPlayer(playerId)

        countGame = games.size
        missedGame = games.filter {
            it.player1Id.equals(player.playerId) or it.player4Id.equals(player.playerId)
        }.size

        profile = Profile(player, games, countGame, missedGame)
    }
}