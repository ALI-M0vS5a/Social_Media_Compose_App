package com.example.socialmedia.feature_chat.presentation.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.socialmedia.feature_chat.components.OwnMessage
import com.example.socialmedia.feature_chat.components.RemoteMessage
import com.example.socialmedia.feature_chat.components.SendTextField
import com.example.socialmedia.feature_chat.domain.model.Message
import com.example.socialmedia.presentation.components.StandardTopBar

@Composable
fun MessageScreen(
    chatId: String,
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit,
    viewModel: MessageScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val messages = remember {
        listOf(
            Message(
                fromId = "",
                toId = "",
                text = "Hey, How's it going?",
                FormattedTime = "19:39",
                chatId = "",
                id = ""
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hey, How's it going?",
                FormattedTime = "19:39",
                chatId = "",
                id = ""
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hey, How's it going?",
                FormattedTime = "19:39",
                chatId = "",
                id = ""
            )
        )
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
                                .data("http://192.168.0.105:8081/profile_pictures/bb4c7b5b-0e3b-4b50-8509-21b2457f0530.png")
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
                        text = "Selena Gomez",
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
                    items(messages) { message ->
                        RemoteMessage(
                            message = message.text,
                            formattedTime = message.FormattedTime
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        OwnMessage(
                            message = message.text,
                            formattedTime = message.FormattedTime
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