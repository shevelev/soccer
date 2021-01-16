package ru.crashdev.soccer.ui.gameplay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_game_play.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player

class GamePlayFragment : Fragment() {

    private val viewModel by viewModel<GamePlayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Игра 2х2"

//        viewModel.loadPlayerList().observe(viewLifecycleOwner, Observer { players ->
//            players?.let {
//                Log.d("qwe", "players list size: ${it.size}")
//                viewModel.gamers = it
//                configureSpinnerAdapters(it)
//            }
//        })

        observerMy()
        configureSpinnerAdapters(viewModel.gamers)
        configureSpinnerListeners()
        configureClickListeners()

    }

    private fun observerMy() {
        viewModel.game.observe(viewLifecycleOwner, {
            showAll(it)
        })
    }

    private fun showAll(it: Game) {
        tv_a1.text = it.player1Point.toString()
        tv_a2.text = it.player2Point.toString()
        tv_b1.text = it.player3Point.toString()
        tv_b2.text = it.player4Point.toString()
        tv_sum_a.text = it.pointA.toString()
        tv_sum_b.text = it.pointB.toString()
        progressBarA.setProgress(it.pointA)
        progressBarB.setProgress(it.pointB)

        if (it.pointA == 10 || it.pointB == 10) {
            showWinners("Game Over")
        }
    }

    companion object {
        fun newInstance() = GamePlayFragment()
    }

    fun showWinners(message: String) {
        this.bt_new_game.visibility = View.VISIBLE
        this.winner.text = message
        disableButtons()
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

    private fun enableButtons() {
        this.bt_a1_plus.isEnabled = true
        this.bt_a2_plus.isEnabled = true
        this.bt_b1_plus.isEnabled = true
        this.bt_b2_plus.isEnabled = true
        this.sp_playerA1.isEnabled = true
        this.sp_playerA2.isEnabled = true
        this.sp_playerB1.isEnabled = true
        this.sp_playerB2.isEnabled = true
    }

    fun configureSpinnerAdapters(list: List<Player>) {

        Log.d("qwe","configureSpinnerAdapters")

        val playersList = list.map { it -> it.playerName }

        sp_playerA1.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        sp_playerA2.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        sp_playerB1.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        sp_playerB2.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
    }

    private fun configureSpinnerListeners() {
        sp_playerA1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.playerSelected("sp_playerA1", position)
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
                viewModel.playerSelected("sp_playerA2", position)
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
                viewModel.playerSelected("sp_playerB1", position)
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
                viewModel.playerSelected("sp_playerB2", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun configureClickListeners() {
        bt_a1_plus.setOnClickListener {
            viewModel.a1_plus(switch1.isChecked)
        }

        bt_a2_plus.setOnClickListener {
            viewModel.a2_plus(switch1.isChecked)
        }
        bt_b1_plus.setOnClickListener {
            viewModel.b1_plus(switch1.isChecked)
        }
        bt_b2_plus.setOnClickListener {
            viewModel.b2_plus(switch1.isChecked)
        }

        bt_new_game.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}