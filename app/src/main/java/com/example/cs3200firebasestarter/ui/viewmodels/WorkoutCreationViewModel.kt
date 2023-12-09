package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.CreateException
import com.example.cs3200firebasestarter.ui.repositories.WorkOutRepository

class WorkoutCreationState {
    var name by mutableStateOf("")
    var reps by mutableIntStateOf(0)
    var sets by mutableIntStateOf(0)
    var weight by mutableIntStateOf(0)
    var description by mutableStateOf("")
    var errorMessage by mutableStateOf("")
}

class WorkoutCreationViewModel(application: Application): AndroidViewModel(application) {
    val uiState = WorkoutCreationState()
    var id: String? = null
    suspend fun init(id: String?){
        if(id == null || id == "new") return
        this.id = id
        val workout = WorkOutRepository.getWorkOuts().find { it.id == id } ?: return
        uiState.name = workout.name ?: ""
        uiState.reps = workout.reps ?: 0
        uiState.sets = workout.sets ?: 0
        uiState.weight = workout.weight ?: 0
        uiState.description = workout.description ?: ""
    }

    suspend fun addWorkout(){
        try {
            if (id == null) {
                WorkOutRepository.createWorkout(
                    uiState.name,
                    uiState.reps,
                    uiState.sets,
                    uiState.weight,
                    uiState.description
                )
            } else {
                val workout = WorkOutRepository.getWorkOuts().find { it.id == id } ?: return
                WorkOutRepository.updateWorkout(
                    workout.copy(
                        name = uiState.name,
                        reps = uiState.reps,
                        sets = uiState.sets,
                        weight = uiState.weight,
                        description = uiState.description
                    )
                )
            }
        }
        catch (e: CreateException){
            uiState.errorMessage = e.message ?: "Error, Try Again"
        }
    }

}