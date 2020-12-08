package ru.crashdev.soccer.ui.mainscreen

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.utils.inflate

class GamesListAdapter: RecyclerView.Adapter<GamesViewHolder>() {

    private var listOfItem = ArrayList<Games>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val inflatedView = parent.inflate(R.layout.games_list_item, false)
        return GamesViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val item = listOfItem[position]
        holder.bindFields(item)
    }

    override fun getItemCount(): Int = listOfItem.size

    fun loadItemList(listOfItem: List<Games>) {
        this.listOfItem = ArrayList(listOfItem)
        notifyDataSetChanged()
    }
}
