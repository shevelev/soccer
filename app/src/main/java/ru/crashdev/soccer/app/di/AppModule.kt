package ru.crashdev.soccer.app.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.local.GamesDao
import ru.crashdev.soccer.repository.local.SoccerDB
import ru.crashdev.soccer.ui.addplayers.AddPlayerViewModel
import ru.crashdev.soccer.ui.gameplay.GamePlayViewModel
import ru.crashdev.soccer.ui.gameslist.GamesListViewModel
import ru.crashdev.soccer.ui.playerslist.PlayersListViewModel
import ru.crashdev.soccer.ui.profile.ProfileViewModel


val viewModelModule = module {
    single { GamesListViewModel(get()) }
    single { GamePlayViewModel(get()) }
    single { PlayersListViewModel(get()) }
    single { AddPlayerViewModel(get()) }
    single { ProfileViewModel(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): SoccerDB {
        return Room.databaseBuilder(application, SoccerDB::class.java, "soccer.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: SoccerDB): GamesDao {
        return database.gamesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideGameRepository(dao: GamesDao): GameRepository {
        return GameRepository(dao)
    }

    single { provideGameRepository(get()) }
}