package com.example.habittracker.domain.repo

import com.example.habittracker.data.models.CompletedHabit
import com.example.habittracker.data.models.HabitEntity
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    val allHabits: Flow<List<HabitEntity>>

    suspend fun getDayCompletedHabits(date: String) : CompletedHabit?

    suspend fun getHabitsByIds(ids: List<Int>): List<HabitEntity>

    suspend fun addHabit(habit: HabitEntity)

    suspend fun deleteHabit(habit: HabitEntity)

    suspend fun updateHabit(habit: HabitEntity)

    suspend fun updateHabitCompletion(habitId: Int, isCompleted: Boolean, date: String)
}
