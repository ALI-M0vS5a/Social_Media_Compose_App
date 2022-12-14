package com.example.socialmedia.feature_post.presentation.create_post

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.StandardButton
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.components.StandardTopBar
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.CropActivityResultContract
import com.example.socialmedia.util.UiEvent
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.util.UUID


@Composable
fun CreatePostScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val imageUri = viewModel.chosenImageUri.value
    val cropActivityLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(16f,9f)
    ) {
        viewModel.onEvent(CreatePostEvents.CropImage(it))
    }
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            cropActivityLauncher.launch(it)
        }

    val systemUiController: SystemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.setStatusBarColor(
            color = Color.Gray
        )
    }
    BackHandler {
        onNavigate(Screen.MainFeedScreen.route)
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.Message -> {
                    Toast.makeText(context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.NavigateUp -> {
                    onNavigate(Screen.MainFeedScreen.route)
                }
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            title = {
                Text(
                    text = "Create Post",
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
            onNavigateIconClick = {
                onNavigate(Screen.MainFeedScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .aspectRatio(16f / 9f)
                .padding(horizontal = 34.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    galleryLauncher.launch("image/*")
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_post),
                tint = Color.Black
            )
            imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(uri)
                            .build()
                    ),
                    contentDescription = stringResource(id = R.string.post_image),
                    modifier = Modifier.matchParentSize()
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        StandardTextField(
            text = viewModel.description.value.text,
            onValueChange = {
                viewModel.onEvent(CreatePostEvents.EnterDescription(it))
            },
            singleLine = false,
            maxLine = 3,
            modifier = Modifier
                .fillMaxWidth(),
            hint = stringResource(id = R.string.description)

        )
        Spacer(modifier = Modifier.height(15.dp))
        StandardButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.post),
            backgroundColor = Color.LightGray,
            contentColor = Color.White,
            textColor = Color.White,
            borderColor = Color.Black,
            onClick = {
                viewModel.onEvent(CreatePostEvents.PostImage)
            },
            enabled = !viewModel.isLoading.value
        )
        Spacer(modifier = Modifier.height(15.dp))
        if(viewModel.isLoading.value) {
            CircularProgressIndicator(
                color = Color(android.graphics.Color.parseColor("#8CDCE1")),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}