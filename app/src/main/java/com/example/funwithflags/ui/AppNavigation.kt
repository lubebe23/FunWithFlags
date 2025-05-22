package com.example.funwithflags.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.funwithflags.ui.QuizScreen
import com.example.funwithflags.ui.StartScreen
import com.example.funwithflags.ui.FlagListScreen.FlagListScreen
import com.example.funwithflags.ui.FlagDetailsScreen.FlagDetailScreen
import com.example.funwithflags.viewmodel.FlagViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: FlagViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "startScreen"
    ) {
        // StartScreen Route
        composable("startScreen") {
            StartScreen(navController = navController)
        }

        // FlagListScreen Route
        composable("flagList") {
            FlagListScreen(
                viewModel = viewModel,
                onItemClick = { flag ->
                    navController.navigate("flagDetail/${flag.id}")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // FlagDetailScreen Route
        composable("flagDetail/{flagId}") { backStackEntry ->
            val flagId = backStackEntry.arguments?.getString("flagId")?.toIntOrNull()
            FlagDetailScreen(
                flagId = flagId,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // QuizScreen Route
        composable("quizScreen") {
            QuizScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onQuizComplete = {
                    navController.navigate("quizComplete")
                }
            )
        }

// QuizCompleteScreen Route
        composable("quizComplete") {
            QuizCompleteScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}
