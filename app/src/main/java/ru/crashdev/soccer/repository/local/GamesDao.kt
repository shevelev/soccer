package ru.crashdev.soccer.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.crashdev.soccer.repository.model.Game
import ru.crashdev.soccer.repository.model.Player

@Dao
interface GamesDao {
    @Query("SELECT * FROM games")
    fun getGamesList(): LiveData<List<Game>>

    @Query("select * from games where gameId= :gameId LIMIT 1")
    fun loadGameById(gameId: Long): LiveData<Game>

    @Query("select * from games where gameId= :gameId LIMIT 1")
    fun loadGameById2(gameId: Long): Game

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Game): Long

    @Query("select * from player order by playerName")
    fun getPlayersList(): LiveData<List<Player>>

    @Query("select * from player where isActive = 1")
    fun getActivePlayersList(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(item: Player)

    @Query("UPDATE Player SET isActive = :checked WHERE playerId = :playerId")
    fun updateActive(playerId: Long, checked: Boolean)

    @Delete
    fun deletePlayer(player: Player)

    @Query("select * from player where playerId = :playerId")
    fun getProfilePlayer(playerId: Long): Player

    @Query("select * from games where player1Id = :playerId or player2Id = :playerId or player3Id = :playerId or player4Id = :playerId")
    fun getGamesByPlayer(playerId: Long): List<Game>

    @Query("update player set scoredBalls = scoredBalls + :goal, missedBalls = missedBalls + :missed WHERE playerId = :playerId")
    fun updatePlayer(playerId: Long, goal: Int, missed: Int)
}
