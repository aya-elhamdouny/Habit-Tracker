package com.example.habittracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habittracker.data.models.CompletedHabit
import com.example.habittracker.data.models.HabitEntity
import com.example.habittracker.utils.ListConverter

@Database(entities = [HabitEntity::class, CompletedHabit::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}
