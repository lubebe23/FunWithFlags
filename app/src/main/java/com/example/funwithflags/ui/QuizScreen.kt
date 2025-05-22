package com.example.funwithflags.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.funwithflags.viewmodel.FlagViewModel
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(
    viewModel: FlagViewModel = viewModel(),
    onBack: () -> Unit,
    onQuizComplete: () -> Unit
) {
    val currentQuestion = viewModel.currentQuestion
    val score by viewModel.score
    val questionCount by viewModel.questionCount

    val totalQuestions = viewModel.quizQuestions.size

    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    LaunchedEffect(showFeedback) {
        if (showFeedback) {
            delay(1500)
            showFeedback = false
            selectedAnswer = null
            if (questionCount >= totalQuestions) {
                onQuizComplete()
            } else {
                viewModel.nextQuestion()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Score: $score/$totalQuestions", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Question $questionCount of $totalQuestions", modifier = Modifier.padding(bottom = 16.dp))

        Card(elevation = CardDefaults.cardElevation(8.dp), modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = currentQuestion.flagImage),
                contentDescription = "Flag to guess",
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Which country does this flag belong to?", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        currentQuestion.shuffledOptions.forEach { option ->
            Button(
                onClick = {
                    if (selectedAnswer == null) {
                        selectedAnswer = option
                        isCorrect = option == currentQuestion.correctAnswer
                        showFeedback = true
                        viewModel.onAnswerSelected(isCorrect)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                enabled = selectedAnswer == null
            ) {
                Text(
                    text = option,
                    color = when {
                        selectedAnswer == option && isCorrect -> Color.Green
                        selectedAnswer == option && !isCorrect -> Color.Red
                        selectedAnswer != null && option == currentQuestion.correctAnswer -> Color.Green
                        else -> Color.White
                    }
                )
            }
        }

        if (showFeedback) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (isCorrect) "Correct! 🎉" else "Wrong! The answer is ${currentQuestion.correctAnswer}",
                color = if (isCorrect) Color.Green else Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Text("Back to Start")
        }
    }
}
