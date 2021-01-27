package ru.crashdev.soccer.ui.addplayers

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import ru.crashdev.soccer.databinding.FragmentAddPlayerBinding
import ru.crashdev.soccer.databinding.FragmentGamePlayBinding
import ru.crashdev.soccer.repository.GameRepository
import ru.crashdev.soccer.repository.model.Player

class AddPlayerViewModel(private val repository: GameRepository) : ViewModel() {

    private lateinit var player: Player
    private var name = ""
    private lateinit var binding: FragmentAddPlayerBinding


    fun setBinder(binder: FragmentAddPlayerBinding) {
        this.binding = binder
        init()
    }

    private fun init() {
        configureClickListeners()
        configureEditText()
    }

    private fun configureClickListeners() {
        binding.btAddPlayer.setOnClickListener {
            savePlayer()
 //           it.onBackPressed()
        }
    }

    private fun configureEditText() {
        binding.etAddPlayerName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateName(s.toString())
            }
        })
    }

    private fun savePlayer() {
        repository.savePlayer(Player(name, 0, 0, true))
    }

    private fun updateName(name: String) {
        this.name = name
    }
}