package com.example.socialmedia.presentation.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val state = rememberPagerState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(android.graphics.Color.parseColor("#C1CED6"))
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(158.dp))
            TopSection(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OnBoardingImageWithTabIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                state = state
            )
            Spacer(modifier = Modifier.height(75.dp))
            GoForwardButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                state = state,
                selectedIndex = state.currentPage,
                navController = navController
            )
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.welcome_to),
        color = Color(android.graphics.Color.parseColor("#000000")),
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 24.sp,
        modifier = modifier
    )
    Text(
        text = stringResource(id = R.string.our_world),
        color = Color(android.graphics.Color.parseColor("#000000")),
        fontWeight = FontWeight(250),
        fontSize = 36.sp,
        lineHeight = 54.sp,
        modifier = modifier
    )
}

@ExperimentalPagerApi
@Composable
fun OnBoardingImageWithTabIndicator(
    modifier: Modifier = Modifier,
    state: PagerState
) {
    HorizontalPager(
        state = state,
        count = 3,
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.onboarding_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
        )
    }
    Spacer(modifier = modifier.height(45.dp))
    DotsIndicator(
        totalDots = 3,
        selectedIndex = state.currentPage,
        selectedColor = Color(android.graphics.Color.parseColor("#4267B2")),
        unSelectedColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                )
            } else {
                Box(
                    modifier = modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                        .border(
                            shape = CircleShape,
                            width = 1.dp,
                            color = Color.Black
                        )
                )
            }
            if(index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun GoForwardButton(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    state: PagerState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    OutlinedButton(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = Color.Black
        ),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = Color.Transparent,
          contentColor = Color.Black
        ),
        modifier = modifier
            .width(219.dp)
            .height(44.dp),
        onClick = {
            scope.launch {
                if (state.currentPage == 2) {
                    navController.navigate(Screen.LoginScreen.route)
                } else {
                    state.animateScrollToPage(
                        page = selectedIndex + 1,
                        pageOffset = 0.001f
                    )
                }
            }
        }
    ){
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.go_forward),
                color = Color(android.graphics.Color.parseColor("#000000")),
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = modifier
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt_24),
                contentDescription = stringResource(id = R.string.go_forward)
            )
        }
    }
}