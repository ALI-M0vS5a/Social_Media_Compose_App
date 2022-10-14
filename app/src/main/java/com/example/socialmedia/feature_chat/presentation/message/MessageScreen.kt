package com.example.socialmedia.feature_chat.presentation.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.feature_chat.presentation.components.OwnMessage
import com.example.socialmedia.feature_chat.presentation.components.RemoteMessage
import com.example.socialmedia.feature_chat.presentation.components.SendTextField
import com.example.socialmedia.presentation.components.StandardTopBar
import okio.ByteString.Companion.decodeBase64
import java.nio.charset.Charset

@Composable
fun MessageScreen(
    remoteUsername: String,
    encodedRemoteUserProfilePictureUrl: String,
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit,
    viewModel: MessageScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pagingState = viewModel.pagingState.value
    val decodedRemoteUserProfilePictureUrl = remember {
        encodedRemoteUserProfilePictureUrl.decodeBase64()?.string(Charset.defaultCharset())
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Color(android.graphics.Color.parseColor("#EDF6F9"))
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            StandardTopBar(
                showBackArrow = true,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color(android.graphics.Color.parseColor("#EDF6F9")),
                navigationIconTint = Color.Black,
                onNavigateIconClick = {
                    onNavigateUp()
                },
                title = {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(context)
                                .data(decodedRemoteUserProfilePictureUrl)
                                .crossfade(true)
                                .build()
                        ),
                        contentDescription = stringResource(id = R.string.profile_pic),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = remoteUsername,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(pagingState.items.size) { i ->
                        val message = pagingState.items[i]
                        if(i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                            viewModel.loadNextMessages()
                        }
                        RemoteMessage(
                            message = message.text,
                            formattedTime = message.formattedTime
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        OwnMessage(
                            message = message.text,
                            formattedTime = message.formattedTime
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
        SendTextField(
            state = viewModel.messageTextFieldState.value,
            onValueChange = {
                viewModel.onEvent(MessageEvent.EnteredMessage(it))
            },
            hint = stringResource(id = R.string.enter_message),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 5.dp),
            onSend = {
                viewModel.onEvent(MessageEvent.SendMessage)
            }
        )
    }
}