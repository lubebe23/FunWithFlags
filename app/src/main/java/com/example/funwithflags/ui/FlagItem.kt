// app/src/main/java/com/example/funwithflags/ui/components/FlagItem.kt
package com.example.funwithflags.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.funwithflags.R
import com.example.funwithflags.data.FlagEntity

@Composable
fun FlagItem(
    flag: FlagEntity,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = flag.imageResId),
                contentDescription = stringResource(R.string.flag_content_desc, flag.countryName),
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = flag.countryName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = flag.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (flag.isFavorite) Icons.Filled.Favorite
                    else Icons.Outlined.Favorite,
                    contentDescription = if (flag.isFavorite)
                        stringResource(R.string.favorited)
                    else
                        stringResource(R.string.not_favorited),
                    tint = if (flag.isFavorite)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}