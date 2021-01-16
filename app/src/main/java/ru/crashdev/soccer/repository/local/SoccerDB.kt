package ru.crashdev.soccer.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player

@Database(entities = [Game::class, Player::class], version = 1, exportSchema = false)
abstract class SoccerDB : RoomDatabase() {
    abstract val gamesDao:  GamesDao
}