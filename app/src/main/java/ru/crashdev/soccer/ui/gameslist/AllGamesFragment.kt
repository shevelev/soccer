package ru.crashdev.soccer.ui.gameslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_games.fab
import kotlinx.android.synthetic.main.fragment_all_games.recyclerViewPlayers
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.R

class AllGamesFragment : Fragment() {

    private val viewModel by viewModel<GamesListViewModel>()
    var gamesListAdapter: GamesListAdapter = GamesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Статистика игр"

        viewModel.getData().observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                gamesListAdapter.loadItemList(games)
            }
        })

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            adapter = gamesListAdapter
            setBackgroundResource(R.drawable.qwe)
        }

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.gamePlayFragment)
        }

    }

    companion object {
        fun newInstance() = AllGamesFragment()
    }
}