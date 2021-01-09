package ru.crashdev.soccer.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.crashdev.soccer.repository.model.Games
import ru.crashdev.soccer.repository.model.Player

@Dao
interface GamesDao {
    @Query("SELECT * FROM games")
    fun getGamesList(): LiveData<List<Games>>

    @Insert
    fun insert(item: Games)

    @Query("select * from player order by playerName")
    fun getPlayersList(): LiveData<List<Player>>

    @Query("select * from player where isActive = 1")
    fun getActivePlayersList(): List<Player>

    @Insert
    fun insertPlayer(item: Player)

    @Query("UPDATE Player SET isActive = :checked WHERE playerId = :playerId")
    fun updateActive(playerId: Long, checked: Boolean)

    @Delete
    fun deletePlayer(player: Player)

    @Query("select * from player where playerId = :playerId")
    fun getProfilePlayer(playerId: Long): Player

    @Query("select * from games")
    fun getGamesByPlayer(): List<Games>

    @Query("update player set scoredBalls = scoredBalls + :goal, missedBalls = missedBalls + :missed WHERE playerId = :playerId")
    fun updatePlayer(playerId: Long, goal: Int, missed: Int)
}
