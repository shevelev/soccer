package ru.crashdev.soccer.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.repository.model.Profile
import ru.crashdev.soccer.ui.gameslist.GamesListAdapter

private const val ARG_PARAM1 = "playerId"

class ProfileFragment : Fragment() {

    private val viewModel by viewModel<ProfileViewModel>()
    private var playerId: Long = 0L

    var gamesListAdapter: GamesListAdapter = GamesListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerId = it.getLong(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Профиль игрока"

        viewModel.loadData(playerId)
        showAll(viewModel.profile)

        recyclerViewPlayers.apply {
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

    companion object {
        fun newInstance(param1: Long) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, param1)
                }
            }
    }

    fun showPlayer(player: Player) {
        this.playerName.text = player.playerName
        this.playerScored.text = player.scoredBalls.toString()
        this.playerMissed.text = player.missedBalls.toString()
    }

    fun showScorredAndMissed(allGames: Int, missedGames: Int, games: List<Game>) {
        this.allGame.text = allGames.toString()
        this.gamesListAdapter.loadItemList(games)
    }

    fun showProgressBars(scored: Double, missed: Double) {

        Log.d("qwe", "scored -> $scored, missed -> $missed")

        this.progress_scored.progress = scored.toInt()
        this.progress_scored_text.text = "$scored%"

        this.progress_def.progress = missed.toInt()
        this.progress_def_text.text = "$missed%"
    }
}