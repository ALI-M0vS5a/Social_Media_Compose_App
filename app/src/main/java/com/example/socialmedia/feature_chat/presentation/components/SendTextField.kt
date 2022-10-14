package com.example.socialmedia.feature_chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.socialmedia.R
import com.example.socialmedia.presentation.util.states.StandardTextFieldState

@Composable
fun SendTextField(
    modifier: Modifier = Modifier,
    state: StandardTextFieldState,
    hint: String = "",
    onValueChange: (String) -> Unit,
    isLoading: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onSend: () -> Unit
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape
                )

                .background(color = Color.White)
        ) {
            OutlinedTextField(
                value = state.text,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .focusRequester(focusRequester = focusRequester),
                placeholder = {
                    Text(text = hint)
                }
            )
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(
                            x = (-30).dp
                        )
                        .size(24.dp),
                    color = Color.Black
                )
            } else {
                FloatingActionButton(
                    onClick = {
                        onSend()
                    },
                    shape = RoundedCornerShape(40),
                    backgroundColor = Color.Black,
                    modifier = Modifier
                        .size(
                            width = 60.26.dp,
                            height = 61.97.dp
                        )
                        .rotate(40f)
                        .offset(
                            x = (-15).dp,
                            y = (12).dp
                        )
                        .align(Alignment.CenterEnd)
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = stringResource(id = R.string.add),
                        tint = if (state.error == null) {
                            Color.White
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier
                            .rotate(-70f)
                            .align(Alignment.Center)
                            .offset(
                                x = 4.dp,
                                y = (-1).dp
                            )
                    )
                }
            }
        }
    }
}