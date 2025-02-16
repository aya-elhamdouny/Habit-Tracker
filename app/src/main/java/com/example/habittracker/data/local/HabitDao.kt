package com.example.habittracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.data.models.CompletedHabit
import com.example.habittracker.data.models.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("SELECT * FROM habits ORDER BY id DESC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id IN (:ids)")
    suspend fun getHabitsByIds(ids: List<Int>): List<HabitEntity>

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedHabit(completedHabit: CompletedHabit)

    @Query("UPDATE habits SET isCompleted = :isCompleted WHERE id = :habitId")
    suspend fun updateHabitCompletion(habitId: Int, isCompleted: Boolean)

    @Query("SELECT * FROM completedHabits Where date = :date")
    suspend fun getDayCompletedHabits(date: String): CompletedHabit?

    @Update
    suspend fun updateCompletedHabits(completedHabit: CompletedHabit)
}

