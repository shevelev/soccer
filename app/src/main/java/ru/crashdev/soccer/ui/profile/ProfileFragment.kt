package ru.crashdev.soccer.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.databinding.FragmentProfileBinding
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player
import ru.crashdev.soccer.repository.model.Profile
import ru.crashdev.soccer.ui.gameslist.GamesListAdapter

private const val ARG_PARAM1 = "playerId"

class ProfileFragment : Fragment() {

    private val viewModel by viewModel<ProfileViewModel>()
    private var playerId: Long = 0L

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerId = it.getLong(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Профиль игрока"

        this.binding.let { viewModel.setBinder(it, playerId) }


    }


    companion object {
        fun newInstance(param1: Long) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, param1)
                }
            }
    }


}