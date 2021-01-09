package ru.crashdev.soccer.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.nav_header.view.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.ProfileContract
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.ui.mainscreen.GamesListAdapter

private const val ARG_PARAM1 = "playerId"

class ProfileFragment : Fragment(), ProfileContract.View {
    private var playerId: Long = 0L
    lateinit var presenter: ProfilePresenter
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
        presenter = ProfilePresenter(view.context)
        presenter.setView(this)

        presenter.loadData(playerId)

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            adapter = gamesListAdapter
        }

    }

    companion object {
        fun newInstance(param1: Long) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, param1)
                }
            }
    }

    override fun showPlayer(player: Player) {
        this.playerName.text = player.playerName
        this.playerScored.text = player.scoredBalls.toString()
        this.playerMissed.text = player.missedBalls.toString()
    }

    override fun showScorredAndMissed(allGames: Int, missedGames: Int, games: List<Games>) {
        this.allGame.text = allGames.toString()
        this.gamesListAdapter.loadItemList(games)
    }

    override fun showProgressBars(scored: Double, missed: Double) {

        Log.d("qwe", "scored -> $scored, missed -> $missed")

        this.progress_scored.progress = scored.toInt()
        this.progress_scored_text.text = "${scored.toInt().toString()}%"

        this.progress_def.progress = missed.toInt()
        this.progress_def_text.text = "${missed.toInt().toString()}%"
    }
}