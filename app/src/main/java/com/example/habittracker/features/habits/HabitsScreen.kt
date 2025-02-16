package com.example.habittracker.features.habits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.habittracker.core.theme.PrimaryColor
import com.example.habittracker.data.models.HabitEntity
import com.example.habittracker.features.habits.component.HabitItem
import com.example.habittracker.features.habits.dialog.AddHabitDialog
import com.example.habittracker.features.habits.dialog.EditHabitDialog
import com.example.habittracker.utils.getCurrentDate

@Composable
fun HabitsScreen(viewModel: HabitViewModel = hiltViewModel()) {
    HabitsContent(viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true , showSystemUi = true)
@Composable
fun HabitsContent(viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState()
    var showDialog by remember { mutableStateOf(false) } // State for dialog visibility
    var habitToEdit by remember { mutableStateOf<HabitEntity?>(null) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Habit Tracker", color = PrimaryColor
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    showDialog = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Habit", tint = PrimaryColor)
                }
            }
        )
    }
    )
    { innerPadding ->
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
                .fillMaxSize(),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    habits.let {
                        items(it.size) { index ->
                            val habit = it[index]
                            HabitItem(
                                habit,
                                onCheckedChange = { updatedHabit ->
                                    viewModel.updateHabitCompletion(
                                        habitId = updatedHabit.id,
                                        isCompleted = updatedHabit.isCompleted,
                                        date = getCurrentDate()
                                    )
                                },
                                onEdit = { habitForEditing ->
                                    habitToEdit = habitForEditing
                                },
                                onDelete = { viewModel.deleteHabit(habit) }
                            )
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        AddHabitDialog(
            onDismiss = { showDialog = false },
            onSaveHabit = { title, description ->
                viewModel.addHabit(title, description)
                showDialog = false
            }
        )
    }
    habitToEdit?.let { habit ->
        EditHabitDialog(
            habit = habit,
            onDismiss = { habitToEdit = null },
            onSaveHabit = { newTitle, newDescription ->
                viewModel.editHabit(habit.copy(title = newTitle, description = newDescription))
                habitToEdit = null
            }
        )
    }
}