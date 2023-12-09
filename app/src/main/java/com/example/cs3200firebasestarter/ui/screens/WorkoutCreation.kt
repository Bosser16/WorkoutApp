package com.example.cs3200firebasestarter.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.repositories.UserRepository
import com.example.cs3200firebasestarter.ui.viewmodels.WorkoutCreationViewModel
import kotlinx.coroutines.launch

@Composable
fun CharacterCreation(navHostController: NavHostController, id: String? = null) {
    val viewModel: WorkoutCreationViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val state = viewModel.uiState

    LaunchedEffect(true){
        viewModel.init(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround) {
        Surface(shadowElevation = 2.dp) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = "Create Character", style = MaterialTheme.typography.headlineSmall)
                FormField(
                    value = state.name,
                    onValueChange = { state.name = it },
                    placeholder = { Text("Name") }
                )
                FormField(
                    value = state.reps.toString(),
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            state.reps = it.toIntOrNull() ?: 0
                        } },
                    placeholder = { Text("Reps") }
                )
                FormField(
                    value = state.sets.toString(),
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            state.sets = it.toIntOrNull() ?: 0
                        } },
                    placeholder = { Text("Sets") }
                )
                FormField(
                    value = state.weight.toString(),
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            state.weight = it.toIntOrNull() ?: 0
                        } },
                    placeholder = { Text("Height (meters)") }
                )
                FormField(
                    value = state.description,
                    onValueChange = { state.description = it },
                    placeholder = { Text("Description") }
                )
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    TextButton(onClick = { navHostController.popBackStack() }) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = {
                            scope.launch {
                                viewModel.addWorkout()
                                if (UserRepository.getCurrentUserId() != null) {
                                    navHostController.navigate(Routes.home.route) {
                                        popUpTo(navHostController.graph.id) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
                Text(
                    text = state.errorMessage,
                    style = TextStyle(color = MaterialTheme.colorScheme.error),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}