package ru.crashdev.soccer.contract

interface AddPlayerContract {
    interface Presenter {
        fun savePlayer()
        fun updateName(name: String)
    }
    interface View {
        fun prompt(string: String?)
    }
}