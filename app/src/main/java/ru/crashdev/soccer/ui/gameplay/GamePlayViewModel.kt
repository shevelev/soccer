package ru.crashdev.soccer.ui.gameplay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.*
import kotlin.coroutines.CoroutineContext

class GamePlayViewModel(private val repository: GameRepository) : ViewModel(), CoroutineScope {

    lateinit var game: LiveData<Game>
    lateinit var tmp_game: Game
    lateinit var gamers: List<Player>

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    init {
        startGame()
    }

//    fun loadPlayerList(): LiveData<List<Player>> {
//        return repository.getDataActivePlayersFromLocal()
//    }

    fun playerSelected(player: String, position: Int) {
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


    fun startGame() {
        val gameId = repository.saveGame(
            Game("", 0, 0, "", 0, 0, "", 0, 0, "", 0, 0, 0, 0)
        )
        tmp_game = repository.loadGame2(gameId)
        game = repository.loadGame(gameId)
        gamers = repository.getDataActivePlayersFromLocal()
    }

    fun a1_plus(checked: Boolean) {
        tmp_game.player1Point++
        tmp_game.pointA++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player1Id} - missed: ${tmp_game.player4Id}")
        repository.updatePlayer(tmp_game.player1Id,1,0)
        repository.updatePlayer(tmp_game.player4Id,0,1)
        repository.saveGame(tmp_game)
    }

    fun a2_plus(checked: Boolean) {
        tmp_game.player2Point++
        tmp_game.pointA++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player2Id} - missed: ${tmp_game.player4Id}")
        repository.updatePlayer(tmp_game.player2Id,1,0)
        repository.updatePlayer(tmp_game.player4Id,0,1)
        repository.saveGame(tmp_game)
    }

    fun b1_plus(checked: Boolean) {
        tmp_game.player3Point++
        tmp_game.pointB++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player3Id} - missed: ${tmp_game.player1Id}")
        repository.updatePlayer(tmp_game.player3Id,1,0)
        repository.updatePlayer(tmp_game.player1Id,0,1)
        repository.saveGame(tmp_game)
    }

    fun b2_plus(checked: Boolean) {
        tmp_game.player4Point++
        tmp_game.pointB++
        Log.d("qwe", "a1 -> goal: ${tmp_game.player4Id} - missed: ${tmp_game.player1Id}")
        repository.updatePlayer(tmp_game.player4Id,1,0)
        repository.updatePlayer(tmp_game.player1Id,0,1)
        repository.saveGame(tmp_game)
    }
}