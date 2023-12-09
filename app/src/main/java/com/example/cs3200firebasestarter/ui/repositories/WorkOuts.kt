package com.example.cs3200firebasestarter.ui.repositories

data class Workout (
    val id: String? = null,
    val name: String? = null,
    val reps: Int? = null,
    val sets: Int? = null,
    val weight: Int? = null,
    val description: String? = null
)

data class WorkoutSet(
    val id: String? = null,
    val date: String? = null,
    val workoutSet: Array<Workout>? = null
)