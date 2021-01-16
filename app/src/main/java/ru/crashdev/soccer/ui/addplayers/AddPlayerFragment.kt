package ru.crashdev.soccer.ui.addplayers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_player.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.crashdev.soccer.R

class AddPlayerFragment : Fragment(){

    private val viewModel by viewModel<AddPlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Добавление игрока"

        configureClickListeners()
        configureEditText()
    }

    private fun configureClickListeners() {
        bt_add_player.setOnClickListener {
            viewModel.savePlayer()
            activity?.onBackPressed()
        }
    }

    private fun configureEditText() {
        et_add_player_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateName(s.toString())
            }
        })
    }

    companion object {
        fun newInstance() = AddPlayerFragment()
    }
}