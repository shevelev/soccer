package ru.crashdev.soccer.ui.gameslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.databinding.GamesListItemBinding
import ru.crashdev.soccer.repository.model.Game

class GamesListAdapter : RecyclerView.Adapter<GamesViewHolder>() {

    private var listOfItem = ArrayList<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = GamesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val item = listOfItem[position]
        holder.bindFields(item)
    }

    override fun getItemCount(): Int = listOfItem.size

    fun loadItemList(listOfItem: List<Game>) {
        this.listOfItem = ArrayList(listOfItem)
        notifyDataSetChanged()
    }
}
