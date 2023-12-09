package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.WorkoutCard
import com.example.cs3200firebasestarter.ui.components.WorkoutSetCard
import com.example.cs3200firebasestarter.ui.viewmodels.WorkoutDisplayViewModel


@Composable
fun AllWorkoutsScreen(navHostController: NavHostController) {
    val viewModel: WorkoutDisplayViewModel = viewModel()
    val state = viewModel.uiState

    LaunchedEffect(key1 = true){
        viewModel.getWorkouts()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "All Workouts",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        LazyColumn{
            items(state.workouts, key={it.id!!}) {
                WorkoutCard(navHostController = navHostController, workout = it)
            }
        }
        if(state.workouts.isEmpty()){
            Text(text = "No Workouts Created Yet",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }
}