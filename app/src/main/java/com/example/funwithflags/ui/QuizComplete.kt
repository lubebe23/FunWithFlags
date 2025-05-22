package com.example.funwithflags.ui



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.funwithflags.viewmodel.FlagViewModel

@Composable
fun QuizCompleteScreen(
    navController: NavHostController,
    viewModel: FlagViewModel = viewModel()
) {
    val score by viewModel.score
    val totalQuestions = viewModel.quizQuestions.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Complete!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Your Score: $score / $totalQuestions", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            viewModel.resetQuiz()
            navController.navigate("startScreen") {
                popUpTo("startScreen") { inclusive = true }
            }
        }) {
            Text("Play Again", fontSize = 18.sp)
        }
    }
}
