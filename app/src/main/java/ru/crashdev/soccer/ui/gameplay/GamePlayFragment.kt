package ru.crashdev.soccer.ui.gameplay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.databinding.FragmentGamePlayBinding
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player

class GamePlayFragment : Fragment() {

    private val viewModel by viewModel<GamePlayViewModel>()
    private var _binding: FragmentGamePlayBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Игра 2х2"

        this.binding.let { viewModel.setBinder(it) }

        observerMy()
        configureSpinnerAdapters(viewModel.gamers)
    }

    private fun observerMy() {
        viewModel.game.observe(viewLifecycleOwner, {
            viewModel.showAll(it)
        })
    }


    companion object {
        fun newInstance() = GamePlayFragment()
    }


    fun configureSpinnerAdapters(list: List<Player>) {

        Log.d("qwe","configureSpinnerAdapters")

        val playersList = list.map { it -> it.playerName }

        binding.spPlayerA1.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        binding.spPlayerA2.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        binding.spPlayerB1.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
        binding.spPlayerB2.adapter = getActivity()?.getBaseContext()?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item, playersList
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}