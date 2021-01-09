package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

interface ProfileContract {
    interface Presenter {
        fun loadData(playerId: Long)
    }
    interface View {
        fun showPlayer(player: Player)
        fun showScorredAndMissed(allGames: Int, missedGames: Int, games: List<Games>)
        fun showProgressBars(scored: Double, missed: Double)
    }
}