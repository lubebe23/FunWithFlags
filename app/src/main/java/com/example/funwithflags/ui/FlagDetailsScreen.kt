

package com.example.funwithflags.ui.FlagDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funwithflags.R
import com.example.funwithflags.data.FlagEntity
import com.example.funwithflags.viewmodel.FlagViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlagDetailScreen(
    flagId: Int?,
    viewModel: FlagViewModel,
    onBackClick: () -> Unit
) {
    var flag by remember { mutableStateOf<FlagEntity?>(null) }

    // Load flag details
    LaunchedEffect(flagId) {
        if (flagId != null) {
            flag = viewModel.getFlagById(flagId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(flag?.countryName ?: stringResource(R.string.flag_details)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            flag?.let { currentFlag ->
                // Flag Image
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = currentFlag.imageResId),
                        contentDescription = stringResource(
                            R.string.flag_content_desc,
                            currentFlag.countryName
                        ),
                        modifier = Modifier
                            .size(300.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Country Information
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = currentFlag.countryName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = currentFlag.description,
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (currentFlag.isFavorite) {
                            stringResource(R.string.favorite_status_favorited)
                        } else {
                            stringResource(R.string.favorite_status_not_favorited)
                        },
                        style = MaterialTheme.typography.labelLarge,
                        color = if (currentFlag.isFavorite) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        }
                    )

                }
            } ?: run {
                // Show error if flag not found
                Text(
                    text = stringResource(R.string.flag_not_found),
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}