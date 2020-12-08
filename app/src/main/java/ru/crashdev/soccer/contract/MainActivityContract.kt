package ru.crashdev.soccer.contract

import androidx.lifecycle.LiveData
import ru.crashdev.soccer.repository.model.Games

interface MainActivityContract {

    interface Presenter {
        fun cleanup()
        fun getData(): LiveData<List<Games>>
        fun handleError(e: Exception)
    }

    interface View {
        fun updateWithData(itemList: List<Games>)
        fun prompt(string: String?)
    }

}
