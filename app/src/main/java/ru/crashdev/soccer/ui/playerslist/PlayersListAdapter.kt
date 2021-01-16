package ru.crashdev.soccer.ui.playerslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.utils.inflate

class PlayersListAdapter(): RecyclerView.Adapter<PlayersViewHolder>() {

    private lateinit var viewModel: PlayersListViewModel
    private var listOfItem = ArrayList<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val inflatedView = parent.inflate(R.layout.players_list_item, false)
        return PlayersViewHolder(inflatedView, viewModel)
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