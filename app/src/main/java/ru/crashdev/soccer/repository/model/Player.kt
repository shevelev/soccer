package ru.crashdev.soccer.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true)
    val playerId: Long,
    val playerName: String,
    val scoredBalls: Int = 0,
    val missedBalls: Int = 0,
    val isActive: Boolean = true
)