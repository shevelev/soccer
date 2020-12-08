package ru.crashdev.soccer.repository.model

object PlayersStore {
    val players: List<String> by lazy {
        val avatars = mutableListOf<String>()
        avatars.add("ШевелёвС")
        avatars.add("КукуевР")
        avatars.add("ГорбуновВ")
        avatars.add("УрихН")
        avatars.add("РоговиковА")
        avatars.add("ГоликовС")
        avatars.add("КиреевП")
        avatars
    }
}