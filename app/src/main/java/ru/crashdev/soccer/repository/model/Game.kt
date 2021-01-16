package ru.crashdev.soccer.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    var player1Name: String,
    var player1Id: Long,
    var player1Point: Int,

    var player2Name: String,
    var player2Id: Long,
    var player2Point: Int,

    var player3Name: String,
    var player3Id: Long,
    var player3Point: Int,

    var player4Name: String,
    var player4Id: Long,
    var player4Point: Int,


    var pointA: Int = 0,
    var pointB: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var gameId: Long = 0
}