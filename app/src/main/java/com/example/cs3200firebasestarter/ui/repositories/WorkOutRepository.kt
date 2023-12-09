package com.example.cs3200firebasestarter.ui.repositories

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class CreateException(message: String?): RuntimeException(message) {}
object WorkOutRepository{
    private val workoutCache = mutableListOf<Workout>()
    private val workoutsetCache = mutableListOf<WorkoutSet>()
    private var cacheInit = false
    private var cacheInitSet = false
    suspend fun getWorkOuts(): List<Workout>{
        if(!cacheInit){
            cacheInit = true
            val snapshot = Firebase.firestore
                .collection("workout")
                .get()
                .await()
            workoutCache.addAll(snapshot.toObjects())
        }
        return workoutCache
    }

    suspend fun getWorkOutSets(): List<WorkoutSet>{
        if(!cacheInitSet){
            cacheInitSet = true
            val snapshot = Firebase.firestore
                .collection("workoutSet")
                .get()
                .await()
            workoutsetCache.addAll(snapshot.toObjects())
        }
        return workoutsetCache
    }
    suspend fun createWorkout(
        name: String,
        reps: Int,
        sets: Int,
        weight: Int,
        description: String
    ): Workout{
        val doc = Firebase.firestore.collection("workout").document()
        val workout = Workout(
            id=doc.id,
            name=name,
            reps=reps,
            sets=sets,
            weight=weight,
            description=description
        )
        doc.set(workout).await()
        workoutCache.add(workout)
        return workout
    }

    suspend fun createWorkoutSet(
        date: String,
        workoutSet: Array<Workout>
    ): WorkoutSet{
        val doc = Firebase.firestore.collection("workoutSet").document()
        val workoutset = WorkoutSet(
            id=doc.id,
            date=date,
            workoutSet=workoutSet
        )
        doc.set(workoutset).await()
        workoutsetCache.add(workoutset)
        return workoutset
    }

    suspend fun updateWorkout(workout: Workout){
        Firebase.firestore.collection("workout").document(workout.id!!).set(workout).await()

        val oldCachIndex = workoutCache.indexOfFirst { it.id == workout.id }
        workoutCache[oldCachIndex] = workout
    }

    suspend fun updateWorkoutSet(workoutset: WorkoutSet){
        Firebase.firestore.collection("workoutSet").document(workoutset.id!!).set(workoutset).await()

        val oldCachIndex = workoutCache.indexOfFirst { it.id == workoutset.id }
        workoutsetCache[oldCachIndex] = workoutset
    }
}