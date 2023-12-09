package com.example.cs3200firebasestarter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.repositories.Workout
import com.example.cs3200firebasestarter.ui.repositories.WorkoutSet
import kotlinx.coroutines.launch

@Composable
fun WorkoutSetCard(navHostController: NavHostController, tmp: WorkoutSet){
    val scope = rememberCoroutineScope()

    val workout1 = Workout(name = "Sit Ups", description = "Do this", reps = 5, sets = 6, weight = 0)
    val workout2 = Workout(name = "Bench", description = "Do that", reps = 4, sets = 3, weight = 135)
    var workoutSet = WorkoutSet(date = "Now", workoutSet = arrayOf(workout1, workout2))

    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround) {
        Surface(shadowElevation = 8.dp) {

        }
    }
}