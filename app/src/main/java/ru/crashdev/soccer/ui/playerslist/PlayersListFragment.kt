package ru.crashdev.soccer.ui.playerslist

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
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.R
import ru.crashdev.soccer.databinding.FragmentPlayersListBinding
import ru.crashdev.soccer.repository.model.Player

class PlayersListFragment : Fragment() {

    private val viewModel by viewModel<PlayersListViewModel>()
//    lateinit var playersListAdapter: PlayersListAdapter

    private var _binding: FragmentPlayersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Игроки"

        this.binding.let { viewModel.setBinder(it) }

//        playersListAdapter = PlayersListAdapter()
//        playersListAdapter.setView(viewModel)

        viewModel.getData().observe(viewLifecycleOwner, Observer { players ->
            players?.let {
                viewModel.items = it as MutableList<Player>
                viewModel.loadItemList(players)
            }
        })

//        binding.recyclerViewGames.apply {
//            hasFixedSize()
//            layoutManager = LinearLayoutManager(this.context)
//            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
//            adapter = playersListAdapter
//        }

//        binding.fab.setOnClickListener {
//            it.findNavController().navigate(R.id.addPlayerFragment)
//        }
//
//        setRecyclerTouchListener()
    }

//    private fun setRecyclerTouchListener() {
//
//        val itemTouchCallback = object : SwipeItemTouchHelper(activity?.applicationContext) {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                viewModel.deletePlayer(viewHolder.adapterPosition)
//            }
//        }
//
//        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
//        itemTouchHelper.attachToRecyclerView(binding.recyclerViewGames)
//    }

    companion object {
        fun newInstance() = PlayersListFragment()
    }
}