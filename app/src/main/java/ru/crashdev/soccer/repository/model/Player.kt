package ru.crashdev.soccer.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    var playerName: String,
    var scoredBalls: Int = 0,
    var missedBalls: Int = 0,
    var isActive: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var playerId: Long = 0
}
