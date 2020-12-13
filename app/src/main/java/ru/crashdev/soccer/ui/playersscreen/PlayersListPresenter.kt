package ru.crashdev.soccer.ui.playersscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.crashdev.soccer.contract.PlayersListContract
import ru.crashdev.soccer.repository.local.LocalRepo
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.utils.BasePresenter
import kotlin.coroutines.CoroutineContext

class PlayersListPresenter(context: Context) : BasePresenter<PlayersListContract.View>(),
    PlayersListContract.Presenter, CoroutineScope {

    private val repository = LocalRepo(context)

    private var items = mutableListOf<Player>()
    private var ctx = context

    override val coroutineContext: CoroutineContext = Dispatchers.Main


    fun setItems(listOfItem: List<Player>) {
        items = listOfItem as MutableList<Player>
    }

    override fun getData(): LiveData<List<Player>> {
        return repository.getDataPlayers()
    }

    override fun updateIsActive(playerId: Long, checked: Boolean) {
        launch(Dispatchers.IO) {
            repository.updateActive(playerId, checked)
        }

        Toast.makeText(ctx, "${playerId}", Toast.LENGTH_SHORT).show()
    }

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int) {
        Toast.makeText(ctx, "${items[pos].playerName}", Toast.LENGTH_SHORT).show()
    }

    override fun onBindItemView(itemView: PlayersListContract.ItemView, pos: Int) {
        itemView.bindItem(items[pos])
    }

    override fun deletePlayer(pos: Int) {
        launch(Dispatchers.IO) {
            repository.deletePlayer(items[pos])
        }
    }
}