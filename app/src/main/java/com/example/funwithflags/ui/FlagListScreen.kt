package com.example.funwithflags.ui.FlagListScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.funwithflags.data.FlagEntity
import com.example.funwithflags.ui.components.FlagItem
import com.example.funwithflags.viewmodel.FlagViewModel

@Composable
fun FlagListScreen(
    viewModel: FlagViewModel = viewModel(),
    onItemClick: (FlagEntity) -> Unit,
    onBackClick: () -> Unit
) {
    val flags by viewModel.allFlags.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Back button
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom=16.dp)
        ) {
            Text("Back")
        }


        // Flag List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(flags) { flag ->
                FlagItem(
                    flag = flag,
                    onItemClick = { onItemClick(flag) },
                    onFavoriteClick = { viewModel.toggleFavorite(flag) }
                )
            }
        }
    }
}