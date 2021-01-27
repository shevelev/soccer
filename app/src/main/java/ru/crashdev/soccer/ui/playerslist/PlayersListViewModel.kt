package ru.crashdev.soccer.ui.playerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.databinding.FragmentAllGamesBinding
import ru.crashdev.soccer.databinding.FragmentPlayersListBinding
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Player

class PlayersListViewModel(private val repository: GameRepository) : ViewModel() {

    var items = mutableListOf<Player>()

    private lateinit var binding: FragmentPlayersListBinding
    private var playersListAdapter: PlayersListAdapter = PlayersListAdapter()

    fun setBinder(binder: FragmentPlayersListBinding) {
        this.binding = binder
        init()
    }

    private fun init() {

        playersListAdapter.setView(this)

        binding.recyclerViewGames.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = playersListAdapter
        }
        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.addPlayerFragment)
        }

        setRecyclerTouchListener()

    }

    private fun setRecyclerTouchListener() {

        val itemTouchCallback = object : SwipeItemTouchHelper(binding.root.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deletePlayer(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewGames)
    }

    fun getData(): LiveData<List<Player>> {
        return repository.getDataPlayersFromLocal()
    }

    private fun deletePlayer(pos: Int) {
        repository.deletePlayer(items[pos])
    }

    fun updateIsActive(playerId: Long, checked: Boolean) {
        repository.updateActive(playerId, checked)
    }

    fun loadItemList(players: List<Player>) {
        playersListAdapter.loadItemList(players)
    }
}