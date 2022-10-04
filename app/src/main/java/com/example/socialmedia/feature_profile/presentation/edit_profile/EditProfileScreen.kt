package com.example.socialmedia.feature_profile.presentation.edit_profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BabyChangingStation
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material.icons.outlined.LinkedCamera
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.StandardButton
import com.example.socialmedia.presentation.components.StandardCenteredTopBar
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.feature_profile.presentation.edit_profile.components.Chip
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        StandardCenteredTopBar(
            onNavigate = onNavigate,
            onNavigatePopBackStack = onNavigatePopBackStack,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.edit_profile),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    lineHeight = 24.sp,
                    color = Color.White
                )
            },
            backgroundColor = Color(android.graphics.Color.parseColor("#C1CED6")),
            modifier = Modifier.fillMaxWidth()
        )
        BannerEditSection(
            profileImage = painterResource(id = R.drawable.profile_pic)
        )
        StandardTextField(
            text = viewModel.uiState.value.username,
            onValueChange = {
                viewModel.setUsername(
                    username = it
                )
            },
            keyboardType = KeyboardType.Text,
            hint = stringResource(id = R.string.username_hint),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = viewModel.uiState.value.github,
            onValueChange = {
                viewModel.setGithubUrlText(
                    githubUrlText = it
                )
            },
            hint = stringResource(id = R.string.github),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Filled.BabyChangingStation
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = viewModel.uiState.value.instagram,
            onValueChange = {
                viewModel.setInstagramUrlText(
                    instagramUrlText = it
                )
            },
            hint = stringResource(id = R.string.instagram),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Outlined.Facebook
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = viewModel.uiState.value.linkedin,
            onValueChange = {
                viewModel.setLinkedinUrlText(
                    linkedinUrlText = it
                )
            },
            hint = stringResource(id = R.string.linkedin),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Outlined.LinkedCamera
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = viewModel.uiState.value.yourBio,
            onValueChange = {
                viewModel.setYourBio(
                    yourBio = it
                )
            },
            hint = stringResource(id = R.string.your_bio),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = false,
            maxLine = 3
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(id = R.string.select),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            mainAxisAlignment = MainAxisAlignment.Center,
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp
        ) {
            listOf(
                "Kotlin",
                "Python",
                "Assembly",
                "C++",
                "C#",
                "TypeScript",
                "Dart",
                "JavaScript",
                "Dart",
                "Java",
                "Ruby",
                "Maya",
                "PHP",
                "CSS"
            ).forEach {
                Chip(
                    text = it
                ) {

                }
            }

        }
        Spacer(modifier = Modifier.height(105.dp))
        StandardButton(
            text = stringResource(id = R.string.save_changes),
            backgroundColor = Color(android.graphics.Color.parseColor("#5151C6")),
            contentColor = Color.White,
            textColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp)
        )
    }

}

@Composable
fun BannerEditSection(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit = {},
    onBannerClick: () -> Unit = {},
    profileImage: Painter
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val profilePictureSize = 90.dp

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((bannerHeight + profilePictureSize) / 2f)
                .background(
                    color = Color(android.graphics.Color.parseColor("#C1CED6"))
                )
        )
        Image(
            painter = profileImage,
            contentDescription = stringResource(id = R.string.profile_pic),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(y = -profilePictureSize / 2f)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color(android.graphics.Color.parseColor("#8CDCE1")),
                    shape = CircleShape
                )

        )
        CameraProfile(
            modifier = Modifier
                .offset(
                    y = -profilePictureSize /1.25f,
                    x = profilePictureSize / 2.3f
                )
        )
    }
}

@Composable
fun CameraProfile(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .wrapContentWidth()
            .wrapContentHeight()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(android.graphics.Color.parseColor("#888BF4")),
                        Color(android.graphics.Color.parseColor("#5151C6"))
                    )
                ),
                shape = RoundedCornerShape(25)
            )
            .clickable {
                onCameraClick()
            }
    ) {
        Icon(
            imageVector = Icons.Outlined.PhotoCamera,
            contentDescription = stringResource(id = R.string.camera_edit_profile),
            modifier = Modifier
                .align(Alignment.Center),
            tint = Color.White
        )
    }
}