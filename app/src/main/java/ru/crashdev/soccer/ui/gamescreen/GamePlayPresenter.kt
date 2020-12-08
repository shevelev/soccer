package ru.crashdev.soccer.ui.gamescreen

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.crashdev.soccer.contract.GamePlayContract
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.*
import ru.crashdev.soccer.utils.BasePresenter
import kotlin.coroutines.CoroutineContext

class GamePlayPresenter(context: Context, private val generator: GameGenerator = GameGenerator()) :
    BasePresenter<GamePlayContract.View>(), GamePlayContract.Presenter, CoroutineScope {

    private var players = GamePlayers()
    private var points = GamePoints()
    private lateinit var game: Games
    private val repository = LocalRepo(context)
    lateinit var gamers: MutableList<Player>

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun playerSelected(player: String, position: Int) {
        when (player) {
            "sp_playerA1" -> players.playerA1 = gamers[position].playerName
            "sp_playerA2" -> players.playerA2 = gamers[position].playerName
            "sp_playerB1" -> players.playerB1 = gamers[position].playerName
            "sp_playerB2" -> players.playerB2 = gamers[position].playerName
        }
        updateGame()
    }

    override fun loadPlayerList(): LiveData<List<Player>> {
        return repository.getDataActivePlayers()
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
        launch(Dispatchers.IO) {
            repository.saveGame(getGame())
        }
    }

    private fun updateGame() {
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
        game = generator.generateGames(
            players,
            points,
            points.point_A1 + points.point_A2,
            points.point_B1 + points.point_B2
        )
        return game
    }
}