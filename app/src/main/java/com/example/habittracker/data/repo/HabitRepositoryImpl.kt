package com.example.habittracker.data.repo

import com.example.habittracker.data.local.HabitDao
import com.example.habittracker.data.models.CompletedHabit
import com.example.habittracker.data.models.HabitEntity
import com.example.habittracker.domain.repo.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(private val habitDao: HabitDao) : HabitRepository {

    override val allHabits: Flow<List<HabitEntity>> = habitDao.getAllHabits()

    override suspend fun getDayCompletedHabits(date: String): CompletedHabit? {
        return habitDao.getDayCompletedHabits(date)
    }

    override suspend fun getHabitsByIds(ids: List<Int>): List<HabitEntity> {
        return habitDao.getHabitsByIds(ids)
    }

    override suspend fun addHabit(habit: HabitEntity) {
        habitDao.insertHabit(habit)
    }

    override suspend fun deleteHabit(habit: HabitEntity) {
        habitDao.deleteHabit(habit)
    }

    override suspend fun updateHabit(habit: HabitEntity) {
        habitDao.updateHabit(habit)
    }

    override suspend fun updateHabitCompletion(habitId: Int, isCompleted: Boolean, date: String) {
        habitDao.updateHabitCompletion(habitId, isCompleted)
        updateDayCompletedHabits(habitId, isCompleted, date)
    }

    private suspend fun updateDayCompletedHabits(
        habitId: Int, isCompleted: Boolean, date: String,
    ) {
        val dayCompletedHabits = habitDao.getDayCompletedHabits(date)
        if (dayCompletedHabits == null) {
            habitDao.insertCompletedHabit(CompletedHabit(date = date, habitsId = listOf(habitId)))
        } else {
            var updatedList = dayCompletedHabits.habitsId.ifEmpty { emptyList() }
            updatedList = if (isCompleted) {
                updatedList + habitId
            } else {
                updatedList - habitId
            }
            val updatedItem = dayCompletedHabits.copy(habitsId = updatedList)
            habitDao.updateCompletedHabits(updatedItem)
        }
    }
}