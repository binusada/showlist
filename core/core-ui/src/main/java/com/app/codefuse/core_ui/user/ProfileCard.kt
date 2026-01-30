package com.app.codefuse.core_ui.user

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.app.codefuse.core_ui.R

@Composable
fun ProfileCard(
    fullName: String,
    email: String,
    location: String,
    imageUrl: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ProfileImage(imageUrl = imageUrl)
            Spacer(modifier = Modifier.width(16.dp))
            UserInfoPanel(fullName = fullName, email = email, location = location)

        }
    }
}

@Composable
fun ProfileImage(imageUrl: String) {
    Log.d("----", imageUrl)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .listener(
                onError = { _, result ->
                    Log.e("---IMAGE_ERROR", "Error loading image: ${result.throwable.message}")
                    Log.e("---IMAGE_ERROR", "Error loading image: ${result.throwable}")
                },
                onSuccess = { _, _ ->
                    Log.d("---IMAGE_SUCCESS", "Image loaded successfully")
                }
            )
            .crossfade(true)
            .build(),
        contentDescription = "Profile Picture",
        placeholder = painterResource(R.drawable.elk_svgrepo_com), // Replace with your placeholder
        error = painterResource(R.drawable.jellyfish_svgrepo_com),
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoPanel(
    fullName: String,
    email: String,
    location: String,
) {
    Column {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = email,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}