package ru.crashdev.soccer.ui.gamescreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.crashdev.soccer.contract.GamePlayContract
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.*
import ru.crashdev.soccer.utils.BasePresenter
import java.util.*
import kotlin.coroutines.CoroutineContext

class GamePlayPresenter(context: Context, private val generator: GameGenerator = GameGenerator()) :
    BasePresenter<GamePlayContract.View>(), GamePlayContract.Presenter, CoroutineScope {

    private var players = GamePlayers()
    private var points = GamePoints()
    private lateinit var game: Games
    private val repository = LocalRepo(context)
    lateinit var gamers: List<Player>

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun playerSelected(player: String, position: Int) {
        when (player) {
            "sp_playerA1" -> players.playerA1 = gamers[position].playerId
            "sp_playerA2" -> players.playerA2 = gamers[position].playerId
            "sp_playerB1" -> players.playerB1 = gamers[position].playerId
            "sp_playerB2" -> players.playerB2 = gamers[position].playerId
        }
        updateGame()
    }

    override fun loadPlayerList() {
        launch(Dispatchers.IO) {
            gamers = repository.getDataActivePlayers()
            withContext(Dispatchers.Main) {
                getView()?.configureSpinnerAdapters(gamers)
            }
        }
    }

    override fun getPla(list: List<Player>) {
        this.gamers = list as MutableList<Player>
        getView()?.configureSpinnerAdapters(list)
    }

    override fun a1_plus(checked: Boolean) {
        when (checked) {
            true -> if (points.point_A1 != 0) points.point_A1--
            false -> points.point_A1++
        }

        updateGame()
    }

    override fun a2_plus(checked: Boolean) {
        when (checked) {
            true -> if (points.point_A2 != 0) points.point_A2--
            false -> points.point_A2++
        }
        updateGame()
    }

    override fun b1_plus(checked: Boolean) {
        when (checked) {
            true -> if (points.point_B1 != 0) points.point_B1--
            false -> points.point_B1++
        }
        updateGame()
    }

    override fun b2_plus(checked: Boolean) {
        when (checked) {
            true -> if (points.point_B2 != 0) points.point_B2--
            false -> points.point_B2++
        }
        updateGame()
    }

    override fun saveGame() {

        Log.d("qwe", "saveGame: ${Date()}")

        launch(Dispatchers.IO) {
            repository.saveGame(game)
            repository.updatePlayer(game.players.playerA1, game.points.point_A1, game.pointB)
            repository.updatePlayer(game.players.playerA2, game.points.point_A2, 0)
            repository.updatePlayer(game.players.playerB1, game.points.point_B1, 0)
            repository.updatePlayer(game.players.playerB2, game.points.point_B2, game.pointA)

            Log.d("qwe", "--> ${game.players.playerA1}, ${game.points.point_A1}, ${game.pointB}")
            Log.d("qwe", "--> ${game.players.playerA2}, ${game.points.point_A2}, 0")
            Log.d("qwe", "--> ${game.players.playerB1}, ${game.points.point_B1}, 0")
            Log.d("qwe", "--> ${game.players.playerB2}, ${game.points.point_B2}, ${game.pointA}")
        }
    }

    private fun updateGame() {
        Log.d("qwe", "updateGame: ${Date()}")
        game = getGame()
        getView()?.showPointsA1(game.points.point_A1)
        getView()?.showPointsA2(game.points.point_A2)
        getView()?.showPointsB1(game.points.point_B1)
        getView()?.showPointsB2(game.points.point_B2)
        getView()?.showPointsA(game.pointA)
        getView()?.showPointsB(game.pointB)

        when (10) {
            game.pointA -> {
                saveGame()
                getView()?.showWinners("Команда А выиграла")
            }
            game.pointB -> {
                saveGame()
                getView()?.showWinners("Команда B выиграла")
            }
        }
    }

    private fun getGame(): Games {
        Log.d("qwe", "getGame: ${Date()}")
        game = generator.generateGames(
            players,
            points,
            points.point_A1 + points.point_A2,
            points.point_B1 + points.point_B2
        )
        return game
    }
}