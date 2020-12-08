package ru.crashdev.soccer.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Games(
    @PrimaryKey(autoGenerate = true)
    val gameId: Long,
    val players: GamePlayers = GamePlayers(),
    val points: GamePoints = GamePoints(),
    val pointA: Int = 0,
    val pointB: Int = 0
)