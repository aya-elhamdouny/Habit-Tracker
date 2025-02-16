package com.example.habittracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completedHabits")
data class CompletedHabit(
    @PrimaryKey
    val date: String,
    val habitsId: List<Int> = emptyList(),
)