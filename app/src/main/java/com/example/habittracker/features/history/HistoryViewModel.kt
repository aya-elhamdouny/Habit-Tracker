package com.example.habittracker.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.models.HabitEntity
import com.example.habittracker.domain.repo.HabitRepository
import com.example.habittracker.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HabitRepository) : ViewModel() {

    var habits: MutableStateFlow<List<HabitEntity>> = MutableStateFlow(emptyList())

    init {
        getDayCompletedHabits(getCurrentDate())
    }

    fun getDayCompletedHabits(date: String) {
        viewModelScope.launch {
            val dayCompletedHabits = repository.getDayCompletedHabits(date)
            if (dayCompletedHabits == null) {
                habits.value = emptyList()
            } else {
                val habitsIds = dayCompletedHabits.habitsId
                val completedHabits = repository.getHabitsByIds(habitsIds)
                habits.value = completedHabits
            }
        }
    }
}