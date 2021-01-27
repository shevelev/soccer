package ru.crashdev.soccer.ui.addplayers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.databinding.FragmentAddPlayerBinding

class AddPlayerFragment : Fragment(){

    private val viewModel by viewModel<AddPlayerViewModel>()
    private var _binding: FragmentAddPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlayerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Добавление игрока"

        this.binding.let { viewModel.setBinder(it) }

    }

    companion object {
        fun newInstance() = AddPlayerFragment()
    }
}