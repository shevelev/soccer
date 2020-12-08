package ru.crashdev.soccer.ui.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.MainActivityContract
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.ui.gamescreen.GamePlay

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private var gamesListAdapter = GamesListAdapter()
    private var presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.setView(this)

        presenter.getData().observe(this, Observer { games ->
            games?.let {
                gamesListAdapter.loadItemList(games)
            }
        })

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = gamesListAdapter
        }

        lifecycle.addObserver(presenter)

        fab.setOnClickListener {
            startActivity(Intent(this, GamePlay::class.java))
        }
    }

    override fun updateWithData(itemList: List<Games>) {
        gamesListAdapter.loadItemList(itemList)
    }

    override fun prompt(string: String?) {
        Log.d("qwe", "------------> $string")
        Snackbar.make(rootLayout, string ?: "-", Snackbar.LENGTH_SHORT).show()
    }


}