package ru.crashdev.soccer.ui.addplayersscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_player.*
import kotlinx.android.synthetic.main.activity_game_play.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.crashdev.soccer.R
import ru.crashdev.soccer.contract.AddPlayerContract
import ru.crashdev.soccer.ui.gamescreen.GamePlayPresenter

class AddPlayer : AppCompatActivity(), AddPlayerContract.View {

    private val presenter = AddPlayerPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_player)

        presenter.setView(this)

        configureUI()
        configureClickListeners()
        configureEditText()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.add_pl)
    }

    private fun configureClickListeners() {
        bt_add_player.setOnClickListener {
            presenter.savePlayer()
            onBackPressed();  //или this.finish или что то свое
        }
    }

    private fun configureEditText() {
        et_add_player_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateName(s.toString())
            }
        })
    }

    override fun prompt(string: String?) {
        Snackbar.make(rootLayout, string ?: "-", Snackbar.LENGTH_SHORT).show()
    }

}