package ru.crashdev.soccer.ui.playersscreen

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.crashdev.soccer.R
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.utils.inflate

class PlayersListAdapter(private val presenter: PlayersListPresenter): RecyclerView.Adapter<PlayersViewHolder>() {
    //private var listOfItem = ArrayList<Player>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val inflatedView = parent.inflate(R.layout.players_list_item, false)
        return PlayersViewHolder(presenter, inflatedView)
       // return PlayersViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        //val item = listOfItem[position]
        val item = presenter.onBindItemView(holder, position)//  listOfItem[position]
        //holder.bindFields(item)
    }

    //override fun getItemCount(): Int =    listOfItem.size
    override fun getItemCount(): Int =  presenter.itemCount ?: 0

    fun loadItemList(listOfItem: List<Player>) {
        Log.d("qwe", "size --> ${listOfItem.size}")
       // this.listOfItem = ArrayList(listOfItem)
        presenter.setItems(listOfItem)
        notifyDataSetChanged()
    }
}