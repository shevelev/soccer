package ru.crashdev.soccer.ui.gameslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.databinding.FragmentAllGamesBinding
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.ui.playerslist.SwipeItemTouchHelper

class GamesListViewModel(private val repository: GameRepository) : ViewModel() {

    var items = mutableListOf<Game>()
    private lateinit var binding: FragmentAllGamesBinding
    var gamesListAdapter: GamesListAdapter = GamesListAdapter()


    fun getData(): LiveData<List<Game>> {
        return repository.getDataGamesFromLocal()
    }

    fun deleteGame(pos: Int) {
        repository.deleteGame(items[pos])
    }


    fun setBinder(binder: FragmentAllGamesBinding) {
        this.binding = binder
        init()
    }

    fun loadItems(listOfItem: List<Game>) {
        gamesListAdapter.loadItemList(listOfItem)
    }

    private fun init() {
        binding.recyclerViewGames.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            gamesListAdapter.loadItemList(items)
            adapter = gamesListAdapter
            setBackgroundResource(R.drawable.qwe)
        }

        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.gamePlayFragment)
        }

        setRecyclerTouchListener()

    }

    private fun setRecyclerTouchListener() {
        val itemTouchCallback = object : SwipeItemTouchHelper(binding.root.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.i("qwe","qwe")
                deleteGame(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewGames)
    }

}