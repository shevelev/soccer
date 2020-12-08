package ru.crashdev.soccer.ui.playersscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_players_list.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.PlayersListContract
import ru.crashdev.soccer.ui.addplayersscreen.AddPlayer

class PlayersList : AppCompatActivity(), PlayersListContract.View {

    private var presenter = PlayersListPresenter(this)

    private var playersListAdapter = PlayersListAdapter(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        presenter.setView(this)
        title = getString(R.string.playersScreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.getData().observe(this, Observer { players ->
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
            startActivity(Intent(this, AddPlayer::class.java))
        }

    }
}