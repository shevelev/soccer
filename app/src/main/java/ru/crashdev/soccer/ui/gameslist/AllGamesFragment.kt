package ru.crashdev.soccer.ui.gameslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.databinding.FragmentAllGamesBinding
import ru.crashdev.soccer.repository.model.Game

class AllGamesFragment : Fragment() {

    private val viewModel by viewModel<GamesListViewModel>()
    private var _binding: FragmentAllGamesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Статистика игр"

        this.binding.let { viewModel.setBinder(it) }

        viewModel.getData().observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                viewModel.items = it as MutableList<Game>
                viewModel.loadItems(it)
            }
        })
    }


    companion object {
        fun newInstance() = AllGamesFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}