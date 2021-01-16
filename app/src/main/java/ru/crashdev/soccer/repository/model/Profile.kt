package ru.crashdev.soccer.repository.model

data class Profile (val player: Player, val games: List<Game>, var countGame: Int = 0 , var missedGame: Int = 0)