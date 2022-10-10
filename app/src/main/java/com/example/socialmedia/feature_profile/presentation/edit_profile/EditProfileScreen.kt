package com.example.socialmedia.feature_profile.presentation.edit_profile

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BabyChangingStation
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material.icons.outlined.LinkedCamera
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.presentation.edit_profile.components.Chip
import com.example.socialmedia.presentation.components.StandardButton
import com.example.socialmedia.presentation.components.StandardCenteredTopBar
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.CropActivityResultContract
import com.example.socialmedia.util.UiEvent
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val profileState = viewModel.uiState.value

    val cropProfilePictureLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(1f, 1f)
    ) {
        viewModel.onEvent(EditProfileEvent.CropProfilePicture(it))
    }
    val cropBannerImageLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(5f, 2f)
    ) {
        viewModel.onEvent(EditProfileEvent.CropBannerImage(it))
    }
    val profilePictureGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) {
        if(it == null) {
            return@rememberLauncherForActivityResult
        }
        cropProfilePictureLauncher.launch(it)
    }
    val bannerImageGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) {
        if(it == null) {
            return@rememberLauncherForActivityResult
        }
        cropBannerImageLauncher.launch(it)
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.NavigateUp -> {
                    onNavigatePopBackStack()
                }
                else -> Unit
            }
        }
    }

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
            profileImage = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(viewModel.uiState.value.profilePictureUri ?: profileState.profile?.profilePictureUrl)
                    .crossfade(true)
                    .build()
            ),
            bannerImage = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(viewModel.uiState.value.bannerUri ?: profileState.profile?.bannerUrl)
                    .crossfade(true)
                    .build()
            ),
            modifier = Modifier,
            onBannerClick = {
                bannerImageGalleryLauncher.launch("image/*")
            },
            onCameraClick = {
                profilePictureGalleryLauncher.launch("image/*")
            }
        )
        StandardTextField(
            text = profileState.username,
            onValueChange = {
                viewModel.onEvent(EditProfileEvent.EnteredUsername(it))
            },
            keyboardType = KeyboardType.Text,
            hint = stringResource(id = R.string.username_hint),
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = Icons.Filled.Person
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = profileState.github,
            onValueChange = {
                viewModel.onEvent(EditProfileEvent.EnteredGitHubUrl(it))
            },
            hint = stringResource(id = R.string.github),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Filled.BabyChangingStation
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = profileState.instagram,
            onValueChange = {
                viewModel.onEvent(EditProfileEvent.EnteredInstagramUrl(it))
            },
            hint = stringResource(id = R.string.instagram),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Outlined.Facebook
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = profileState.linkedin,
            onValueChange = {
                viewModel.onEvent(EditProfileEvent.EnteredLinkedinUrl(it))
            },
            hint = stringResource(id = R.string.linkedin),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = Icons.Outlined.LinkedCamera
        )
        Spacer(modifier = Modifier.height(25.dp))
        StandardTextField(
            text = profileState.yourBio,
            onValueChange = {
                viewModel.onEvent(EditProfileEvent.EnteredBio(it))
            },
            hint = stringResource(id = R.string.your_bio),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = false,
            maxLine = 3,
            leadingIcon = Icons.Filled.Description
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
            viewModel.uiState.value.skills.forEach { skill ->
                Chip(
                    text = skill.name,
                    selected = skill in viewModel.uiState.value.selectedSkills,
                    onChipClick = {
                        viewModel.onEvent(EditProfileEvent.SetSkillSelected(skill))
                    }
                )
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
                .padding(bottom = 25.dp),
            onClick = {
                viewModel.onEvent(EditProfileEvent.UpdateProfile)
            }
        )
    }

}

@Composable
fun BannerEditSection(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit = {},
    onBannerClick: () -> Unit = {},
    bannerImage: Painter,
    profileImage: Painter
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val profilePictureSize = 90.dp

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((bannerHeight + profilePictureSize) / 2f)
                .clickable { onBannerClick() }
        ) {
            Image(
                painter = bannerImage,
                contentDescription = stringResource(id = R.string.banner_pic),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bannerHeight)
            )
        }
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
                .clickable { onCameraClick() }

        )
        CameraProfile(
            modifier = Modifier
                .offset(
                    y = -profilePictureSize / 1.25f,
                    x = profilePictureSize / 2.3f
                )
                .clickable { onCameraClick() }
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