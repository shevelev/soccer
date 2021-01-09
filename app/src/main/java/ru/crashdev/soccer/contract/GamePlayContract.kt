package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Player

interface GamePlayContract {


    interface Presenter {
        fun playerSelected(player: String, position: Int)
        fun loadPlayerList()
        fun getPla(list: List<Player>)
        fun a1_plus(checked: Boolean)
        fun a2_plus(checked: Boolean)
        fun b1_plus(checked: Boolean)
        fun b2_plus(checked: Boolean)
        fun saveGame()
    }

    interface View {
        fun showPointsA1(point: Int)
        fun showPointsA2(point: Int)
        fun showPointsB1(point: Int)
        fun showPointsB2(point: Int)
        fun showPointsA(point: Int)
        fun showPointsB(point: Int)
        fun showWinners(message: String)
        fun configureSpinnerAdapters(list: List<Player>)
        //fun disableButtons()
    }
}