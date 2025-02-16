package com.example.habittracker.features.habits.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.core.theme.PrimaryColor
import com.example.habittracker.core.theme.TextSecondaryColor
import com.example.habittracker.core.theme.TextTitleColor
import com.example.habittracker.data.models.HabitEntity

@Composable
fun HabitItem(
    habit: HabitEntity,
    onCheckedChange: (HabitEntity) -> Unit,
    onDelete: (HabitEntity) -> Unit,
    onEdit: (HabitEntity) -> Unit

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(2.dp, PrimaryColor, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Group for the checkbox and texts, takes up most of the width
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = habit.isCompleted,
                    onCheckedChange = { isChecked ->
                        onCheckedChange(habit.copy(isCompleted = isChecked))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryColor,
                        uncheckedColor = TextSecondaryColor
                    )
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = habit.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextTitleColor
                    )
                    habit.description?.takeIf { it.isNotBlank() }?.let { desc ->
                        Text(
                            text = desc,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = TextSecondaryColor,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            IconButton(onClick = { onEdit(habit) }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Habit",
                    tint = PrimaryColor
                )
            }
            /* IconButton(onClick = { onDelete(habit) }) {
                 Icon(
                     imageVector = Icons.Filled.Delete,
                     contentDescription = "Delete Habit",
                     tint = PrimaryColor
                 )
             }*/
        }
    }
}
