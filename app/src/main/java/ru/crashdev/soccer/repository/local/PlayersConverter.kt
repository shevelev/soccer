package ru.crashdev.soccer.repository.local

import androidx.room.TypeConverter
import ru.crashdev.soccer.repository.model.GamePlayers
import ru.crashdev.soccer.repository.model.GamePoints
import java.util.*

class GamePlayersConverter {
    @TypeConverter
    fun fromGamePlayers(players: GamePlayers?): String? {
        if (players != null) {
            return String.format(
                Locale.US,
                "%s,%s,%s,%s",
                players.playerA1,
                players.playerA2,
                players.playerB1,
                players.playerB2
            )
        }
        return null
    }

    @TypeConverter
    fun toGamePlayers(value: String?): GamePlayers? {
        if (value != null) {
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return GamePlayers(pieces[0], pieces[1], pieces[2], pieces[3])
        }
        return null
    }

    @TypeConverter
    fun fromGamePoints(points: GamePoints?): String? {
        if (points != null) {
            return String.format(
                Locale.US,
                "%s,%s,%s,%s",
                points.point_A1,
                points.point_A2,
                points.point_B1,
                points.point_B2
            )
        }
        return null
    }

    @TypeConverter
    fun toGamePoints(value: String?): GamePoints? {
        if (value != null) {
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return GamePoints(
                pieces[0].toInt(),
                pieces[1].toInt(),
                pieces[2].toInt(),
                pieces[3].toInt()
            )
        }
        return null
    }
}
