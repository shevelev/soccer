package ru.crashdev.soccer.ui.mainscreen

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import ru.crashdev.soccer.contract.*
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.utils.BasePresenter
import kotlin.coroutines.CoroutineContext

class MainActivityPresenter(context: Context) :
    DefaultLifecycleObserver, CoroutineScope,
    BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter{


    private val coroutineJob = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + coroutineJob

    private val repository = LocalRepo(context)

    override fun getData(): LiveData<List<Games>> {
        return repository.getDataGames()
    }

    override fun handleError(e: Exception) {
        getView()?.prompt(e.message)
    }

    override fun cleanup() {
        coroutineContext.cancel()
    }
}