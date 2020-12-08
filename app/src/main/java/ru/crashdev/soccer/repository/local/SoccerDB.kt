package ru.crashdev.soccer.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

@Database(entities = [Games::class, Player::class], version = 1, exportSchema = false)
@TypeConverters(GamePlayersConverter::class)
abstract class SoccerDB : RoomDatabase() {
    abstract fun gamesDao(): GamesDao

    companion object {
        private var INSTANCE: SoccerDB? = null

        fun getInstance(context: Context): SoccerDB {
            if (INSTANCE == null) {
                synchronized(SoccerDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SoccerDB::class.java, "soccer.db")
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}