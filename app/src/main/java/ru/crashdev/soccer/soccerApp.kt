package ru.crashdev.soccer

import android.app.Application
import androidx.room.Room
import ru.crashdev.soccer.repository.local.GamesDao
import ru.crashdev.soccer.repository.local.SoccerDB

class soccerApp :  Application() {

    companion object {
        lateinit var database : GamesDao
    }

    override fun onCreate() {
        super.onCreate()
        database = SoccerDB.getInstance(this)!!.gamesDao()
//            Room.databaseBuilder(this, CreatureDatabase::class.java, "creature_database")
//            .build()
    }
}