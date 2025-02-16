package com.example.habittracker.features.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.habittracker.features.history.component.CalendarWidget
import com.example.habittracker.features.history.component.CompletedHabitItem
import com.example.habittracker.utils.formatLocalDate


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {

    val habits by viewModel.habits.collectAsState()

    Scaffold { innerPadding ->
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
            Column {
                CalendarWidget(onSelectedDataChange = {
                    viewModel.getDayCompletedHabits(it.date.formatLocalDate())
                })
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    habits.let {
                        items(it.size) { index ->
                            val habit = it[index]
                            CompletedHabitItem(habit)
                        }
                    }
                }
            }
        }
    }
}