package ru.crashdev.soccer.ui.gameplay

import android.R
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.crashdev.soccer.databinding.FragmentGamePlayBinding
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.*
import kotlin.coroutines.CoroutineContext

class GamePlayViewModel(private val repository: GameRepository) : ViewModel(), CoroutineScope {

    lateinit var game: LiveData<Game>
    lateinit var tmp_game: Game
    lateinit var gamers: List<Player>
    private lateinit var binding: FragmentGamePlayBinding

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    fun setBinder(binder: FragmentGamePlayBinding) {
        this.binding = binder
        init()
    }

    private fun init() {
        startGame()
        configureSpinnerListeners()
        configureClickListeners()
    }

    fun showAll(it: Game) {
        binding.tvA1.text = it.player1Point.toString()
        binding.tvA2.text = it.player2Point.toString()
        binding.tvB1.text = it.player3Point.toString()
        binding.tvB2.text = it.player4Point.toString()
        binding.tvSumA.text = it.pointA.toString()
        binding.tvSumB.text = it.pointB.toString()
        binding.progressBarA.progress = it.pointA
        binding.progressBarB.progress = it.pointB

        if (it.pointA == 10 || it.pointB == 10) {
            showWinners("Game Over")
        }
    }


    private fun showWinners(message: String) {
        binding.btNewGame.visibility = View.VISIBLE
        binding.winner.text = message
        disableButtons()
    }

    private fun disableButtons() {
        binding.btA1Plus.isEnabled = false
        binding.btA2Plus.isEnabled = false
        binding.btB1Plus.isEnabled = false
        binding.btB2Plus.isEnabled = false
        binding.spPlayerA1.isEnabled = false
        binding.spPlayerA2.isEnabled = false
        binding.spPlayerB1.isEnabled = false
        binding.spPlayerB2.isEnabled = false
    }

    private fun configureSpinnerListeners() {
        binding.spPlayerA1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                playerSelected("sp_playerA1", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spPlayerA2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                playerSelected("sp_playerA2", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spPlayerB1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                playerSelected("sp_playerB1", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spPlayerB2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                playerSelected("sp_playerB2", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun configureClickListeners() {
        binding.btA1Plus.setOnClickListener {
            a1_plus(binding.switch1.isChecked)
        }

        binding.btA2Plus.setOnClickListener {
            a2_plus(binding.switch1.isChecked)
        }
        binding.btB1Plus.setOnClickListener {
            b1_plus(binding.switch1.isChecked)
        }
        binding.btB2Plus.setOnClickListener {
            b2_plus(binding.switch1.isChecked)
        }

//        binding.btNewGame.setOnClickListener {
//            onBackPressed()
//        }
    }

    private fun playerSelected(player: String, position: Int) {
        Log.d("qwe", "selected -> ${gamers[position].playerName}")
        when (player) {
            "sp_playerA1" -> {
                tmp_game.player1Name = gamers[position].playerName
                tmp_game.player1Id = gamers[position].playerId
            }
            "sp_playerA2" -> {
                tmp_game.player2Name = gamers[position].playerName
                tmp_game.player2Id = gamers[position].playerId
            }
            "sp_playerB1" -> {
                tmp_game.player3Name = gamers[position].playerName
                tmp_game.player3Id = gamers[position].playerId
            }
            "sp_playerB2" -> {
                tmp_game.player4Name = gamers[position].playerName
                tmp_game.player4Id = gamers[position].playerId
            }
        }
        repository.saveGame(tmp_game)
    }


    private fun startGame() {
        val gameId = repository.saveGame(
            Game("", 0, 0, "", 0, 0, "", 0, 0, "", 0, 0, 0, 0)
        )
        tmp_game = repository.loadGame2(gameId)
        game = repository.loadGame(gameId)
        gamers = repository.getDataActivePlayersFromLocal()
    }

    private fun a1_plus(checked: Boolean) {
        tmp_game.player1Point++
        tmp_game.pointA++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player1Id} - missed: ${tmp_game.player4Id}")
        repository.updatePlayer(tmp_game.player1Id,1,0)
        repository.updatePlayer(tmp_game.player4Id,0,1)
        repository.saveGame(tmp_game)
    }

    private fun a2_plus(checked: Boolean) {
        tmp_game.player2Point++
        tmp_game.pointA++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player2Id} - missed: ${tmp_game.player4Id}")
        repository.updatePlayer(tmp_game.player2Id,1,0)
        repository.updatePlayer(tmp_game.player4Id,0,1)
        repository.saveGame(tmp_game)
    }

    private fun b1_plus(checked: Boolean) {
        tmp_game.player3Point++
        tmp_game.pointB++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player3Id} - missed: ${tmp_game.player1Id}")
        repository.updatePlayer(tmp_game.player3Id,1,0)
        repository.updatePlayer(tmp_game.player1Id,0,1)
        repository.saveGame(tmp_game)
    }

    private fun b2_plus(checked: Boolean) {
        tmp_game.player4Point++
        tmp_game.pointB++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player4Id} - missed: ${tmp_game.player1Id}")
        repository.updatePlayer(tmp_game.player4Id,1,0)
        repository.updatePlayer(tmp_game.player1Id,0,1)
        repository.saveGame(tmp_game)
    }
}