package ru.crashdev.soccer.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_all_games.fab
import kotlinx.android.synthetic.main.fragment_all_games.recyclerViewPlayers
import kotlinx.android.synthetic.main.fragment_all_games.rootLayout
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.MainActivityContract
import ru.crashdev.soccer.repository.model.Games

class AllGamesFragment : Fragment(), MainActivityContract.View {

    var gamesListAdapter: GamesListAdapter = GamesListAdapter()
    lateinit var presenter: MainActivityPresenter

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

        presenter = MainActivityPresenter(view.context)
        presenter.setView(this)

        presenter.getData().observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                gamesListAdapter.loadItemList(games)
            }
        })

        recyclerViewPlayers.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this.context)
            //addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = gamesListAdapter
        }

        lifecycle.addObserver(presenter)

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.gamePlayFragment)
        }

    }

    companion object {
        fun newInstance() = AllGamesFragment()
    }

    override fun updateWithData(itemList: List<Games>) {
        gamesListAdapter.loadItemList(itemList)
    }

    override fun prompt(string: String?) {
        Snackbar.make(rootLayout, string ?: "-", Snackbar.LENGTH_SHORT).show()
    }
}