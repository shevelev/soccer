package ru.crashdev.soccer.repository.model

class GameGenerator {
    fun generateGames(players: GamePlayers, points: GamePoints, pointA: Int = 0, pointB: Int = 0): Games {
        val sumPointA = points.point_A1 + points.point_A2
        val sumPointB = points.point_B1 + points.point_B2
        return Games(0, players, points, sumPointA, sumPointB)
    }

    fun generatePlayer(name: String): Player {
        return Player(0,name,0,0,true)
    }
}
