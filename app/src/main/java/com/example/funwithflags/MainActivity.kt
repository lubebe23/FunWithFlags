/*  Title: Fun With Flags
    Author: Lewis Ubebe
    Description: An Android application that allows the user to learn about different flags
                 in a database repository. The app also features a quiz where the user must
                 test their knowledge on flags.

 */

package com.example.funwithflags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.funwithflags.data.FlagDatabase
import com.example.funwithflags.data.FlagEntity
import com.example.funwithflags.ui.AppNavigation
import com.example.funwithflags.ui.theme.FunWithFlagsTheme
import com.example.funwithflags.viewmodel.FlagViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get data -> database
        val database = FlagDatabase.getDatabase(applicationContext)

        CoroutineScope(Dispatchers.IO).launch {
            val dao = database.flagDao()
            val flags = dao.getAllFlags().first()
            if (flags.isEmpty()) {
                dao.insertFlags(FlagEntity.DEFAULT_FLAGS)
            }
        }

        setContent {
            FunWithFlagsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: FlagViewModel = viewModel()
                    AppNavigation(navController, viewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    FunWithFlagsTheme {
        Surface {
            val navController = rememberNavController()
            val viewModel: FlagViewModel = viewModel()
            AppNavigation(navController, viewModel)
        }
    }
}