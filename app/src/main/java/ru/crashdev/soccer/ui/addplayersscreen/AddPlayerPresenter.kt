package ru.crashdev.soccer.ui.addplayersscreen

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.crashdev.soccer.contract.AddPlayerContract
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.GameGenerator
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.utils.BasePresenter
import kotlin.coroutines.CoroutineContext

class AddPlayerPresenter(
    context: Context,
    private val generator: GameGenerator = GameGenerator()
) : BasePresenter<AddPlayerContract.View>(), AddPlayerContract.Presenter, CoroutineScope {

    private val repository = LocalRepo(context)
    private lateinit var player: Player
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private var name = ""

    override fun savePlayer() {
        launch(Dispatchers.IO) {
            repository.savePlayer(player)
        }
    }

    override fun updateName(name: String) {
        this.name = name
        updatePlayer()
    }

    private fun updatePlayer() {
        player = generator.generatePlayer(name)
    }

}