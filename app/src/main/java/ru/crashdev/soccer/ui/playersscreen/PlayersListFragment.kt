package ru.crashdev.soccer.ui.playersscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_players_list.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.PlayersListContract

class PlayersListFragment : Fragment(), PlayersListContract.View {

    lateinit var presenter: PlayersListPresenter
    lateinit var playersListAdapter: PlayersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Игроки"

        presenter = PlayersListPresenter(view.context)
        presenter.setView(this)

        playersListAdapter = PlayersListAdapter(presenter)

        presenter.getData().observe(viewLifecycleOwner, Observer { players ->
            players?.let {
                playersListAdapter.loadItemList(players)
            }
        })

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = playersListAdapter
        }

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.addPlayerFragment)
        }

        setRecyclerTouchListener()
    }

    private fun setRecyclerTouchListener() {

        val itemTouchCallback = object : SwipeItemTouchHelper(activity?.applicationContext) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter.deletePlayer(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewPlayers)
    }

    companion object {
        fun newInstance() = PlayersListFragment()
    }
}