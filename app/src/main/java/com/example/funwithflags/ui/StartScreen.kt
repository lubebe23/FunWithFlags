package com.example.funwithflags.ui



import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.funwithflags.R

@Composable
fun StartScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo (Image of Earth)
        Image(
            painter = painterResource(id = R.drawable.earth),
            contentDescription = "Earth Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 24.dp)
        )

        // Title
        Text(
            text = "Fun With Flags",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Button to navigate to FlagListScreen
        Button(
            onClick = {
                navController.navigate("flagList")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Flags", fontSize = 18.sp)
        }

        // Button to navigate to QuizScreen
        Button(
            onClick = {
                navController.navigate("quizScreen")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Quiz", fontSize = 18.sp)
        }
    }
}
