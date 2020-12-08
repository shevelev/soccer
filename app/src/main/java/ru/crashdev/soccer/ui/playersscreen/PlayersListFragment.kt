package ru.crashdev.soccer.ui.playersscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_players_list.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.PlayersListContract
import ru.crashdev.soccer.ui.addplayersscreen.AddPlayer

class PlayersListFragment : Fragment(), PlayersListContract.View {

    lateinit var presenter: PlayersListPresenter
    lateinit var playersListAdapter: PlayersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = PlayersListPresenter(view.context)
        presenter.setView(this)

        playersListAdapter = PlayersListAdapter(presenter)

        presenter.getData().observe(viewLifecycleOwner, Observer { players ->
            players?.let {
                it.forEach { Log.d("qwe", "${it.playerId} - ${it.playerName}") }
                playersListAdapter.loadItemList(players)
            }
        })

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = playersListAdapter
        }

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.addPlayerFragment)
        }
    }

    companion object {
        fun newInstance() = PlayersListFragment()
    }
}