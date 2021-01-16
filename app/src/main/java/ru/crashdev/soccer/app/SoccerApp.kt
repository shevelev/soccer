package ru.crashdev.soccer.app

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.crashdev.soccer.app.di.databaseModule
import ru.crashdev.soccer.app.di.repositoryModule
import ru.crashdev.soccer.app.di.viewModelModule

class soccerApp :  Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@soccerApp)
            modules(listOf(repositoryModule, databaseModule, viewModelModule))
        }

        val initializerBuilder = Stetho.newInitializerBuilder(this)

        initializerBuilder.enableWebKitInspector(
            Stetho.defaultInspectorModulesProvider(this)
        )

        initializerBuilder.enableDumpapp(
            Stetho.defaultDumperPluginsProvider(this)
        )

        val initializer = initializerBuilder.build()

        Stetho.initialize(initializer)

    }
}