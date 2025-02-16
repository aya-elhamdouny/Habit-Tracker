package com.example.habittracker.features.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.models.HabitEntity
import com.example.habittracker.domain.repo.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(private val repository: HabitRepository) : ViewModel() {

    val habits: StateFlow<List<HabitEntity>> = repository.allHabits
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addHabit(title: String, description: String) {
        viewModelScope.launch {
            repository.addHabit(HabitEntity(title = title, description = description))
        }
    }

    fun deleteHabit(habit: HabitEntity) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    fun editHabit(updatedHabit: HabitEntity) {
        viewModelScope.launch {
            repository.updateHabit(updatedHabit)
        }
    }

    fun updateHabitCompletion(habitId: Int, isCompleted: Boolean, date: String) {
        viewModelScope.launch {
            repository.updateHabitCompletion(habitId, isCompleted, date)
        }
    }
}
