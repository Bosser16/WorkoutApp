package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.WorkOutRepository
import com.example.cs3200firebasestarter.ui.repositories.Workout


class WorkoutScreenState {
    val _workouts = mutableStateListOf<Workout>()
    val workouts: List<Workout> get() = _workouts
}
class WorkoutDisplayViewModel(application: Application): AndroidViewModel(application) {
    val uiState = WorkoutScreenState()
    suspend fun getWorkouts(){
        val characters = WorkOutRepository.getWorkOuts()
        uiState._workouts.clear()
        uiState._workouts.addAll(characters)
    }
}