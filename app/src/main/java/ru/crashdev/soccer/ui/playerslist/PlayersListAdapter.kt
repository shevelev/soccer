package ru.crashdev.soccer.ui.playerslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.databinding.GamesListItemBinding
import ru.crashdev.soccer.databinding.PlayersListItemBinding
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.ui.gameslist.GamesViewHolder
import ru.crashdev.soccer.utils.inflate

class PlayersListAdapter(): RecyclerView.Adapter<PlayersViewHolder>() {

    private lateinit var viewModel: PlayersListViewModel
    private var listOfItem = ArrayList<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = PlayersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(binding,viewModel)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val item = listOfItem[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int =  listOfItem.size

    fun loadItemList(listOfItem: List<Player>) {
       this.listOfItem = ArrayList(listOfItem)
        notifyDataSetChanged()
    }

    fun setView(viewModel: PlayersListViewModel) {
        this.viewModel = viewModel
    }
}