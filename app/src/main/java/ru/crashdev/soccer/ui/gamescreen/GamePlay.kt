package ru.crashdev.soccer.ui.gamescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_game_play.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.GamePlayContract
import ru.crashdev.soccer.repository.model.Player

class GamePlay : AppCompatActivity(), GamePlayContract.View {

    private val presenter = GamePlayPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)

        presenter.setView(this)


        configureUI()
        configureSpinnerListeners()
        configureClickListeners()

        presenter.loadPlayerList().observe(this, Observer { players ->
            players?.let {
                it.forEach { Log.d("qwe", "${it.playerId} - ${it.playerName}") }
                presenter.getPla(players)
            }
        })

    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.games_2x2)
    }

    private fun configureSpinnerListeners() {
        sp_playerA1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.playerSelected("sp_playerA1", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        sp_playerA2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.playerSelected("sp_playerA2", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        sp_playerB1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.playerSelected("sp_playerB1", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        sp_playerB2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.playerSelected("sp_playerB2", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    override fun showPointsA1(point: Int) {
        this.tv_a1.text = point.toString()
    }

    override fun showPointsA2(point: Int) {
        this.tv_a2.text = point.toString()
    }

    override fun showPointsB1(point: Int) {
        this.tv_b1.text = point.toString()
    }

    override fun showPointsB2(point: Int) {
        this.tv_b2.text = point.toString()
    }

    override fun showPointsA(point: Int) {
        this.tv_sum_a.text = point.toString()
        this.progressBarA.setProgress(point)
    }

    override fun showPointsB(point: Int) {
        this.tv_sum_b.text = point.toString()
        this.progressBarB.setProgress(point)
    }

    override fun showWinners(message: String) {
        this.bt_new_game.visibility = View.VISIBLE
        this.winner.text = message
        disableButtons()
    }

    override fun configureSpinnerAdapters(list: List<Player>) {
        val playersList = list.map { it -> it.playerName }

        sp_playerA1.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, playersList
        )

        sp_playerA2.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, playersList
        )
        sp_playerB1.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, playersList
        )
        sp_playerB2.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, playersList
        )
    }

    private fun disableButtons() {
        this.bt_a1_plus.isEnabled = false
        this.bt_a2_plus.isEnabled = false
        this.bt_b1_plus.isEnabled = false
        this.bt_b2_plus.isEnabled = false
        this.sp_playerA1.isEnabled = false
        this.sp_playerA2.isEnabled = false
        this.sp_playerB1.isEnabled = false
        this.sp_playerB2.isEnabled = false
    }

    private fun configureClickListeners() {
        bt_a1_plus.setOnClickListener {
            presenter.a1_plus(switch1.isChecked)
        }

        bt_a2_plus.setOnClickListener {
            presenter.a2_plus(switch1.isChecked)
        }
        bt_b1_plus.setOnClickListener {
            presenter.b1_plus(switch1.isChecked)
        }
        bt_b2_plus.setOnClickListener {
            presenter.b2_plus(switch1.isChecked)
        }

        bt_new_game.setOnClickListener {
            presenter.saveGame()
        }
    }

}