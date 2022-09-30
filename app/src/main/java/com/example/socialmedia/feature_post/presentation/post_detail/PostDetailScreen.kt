package com.example.socialmedia.feature_post.presentation.post_detail


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.socialmedia.presentation.components.StandardTopBar


@Composable
fun PostDetailScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            navActions = {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = {  }) {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = CircleShape
                                )
                                .size(20.dp)


                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.add),
                                tint = Color.Black,
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.FileUpload,
                            contentDescription = stringResource(id = R.string.upload),
                            tint = Color.Black
                        )
                    }
                }
            },
            showBackArrow = true,
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color.White,
            navigationIconTint = Color.Black,
            onNavigateIconClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ){
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        TopPostSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.75.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.post),
                                contentDescription = stringResource(id = R.string.post_detail_image),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        EngagementsBottomPost(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quis risus, neque cursus risus. Eget dictumst vitae enim, felis morbi. Quis risus, neque cursus risus. Eget dictumst vitae enim, felis morbi. Quis risus, neque cursus risus. ",
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            color = Color(android.graphics.Color.parseColor("#828282")),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
            }
            items(20){
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 14.dp,
                            vertical = 10.dp
                        )
                        .height(104.dp)
                )
            }
        }
    }


}

@Composable
fun TopPostSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = stringResource(id = R.string.profile_pic),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .align(Alignment.Center)

                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Monica Gamage",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = Color(android.graphics.Color.parseColor("#242424")),
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun EngagementsBottomPost(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ){
            Row {
                Text(
                    text = "20",
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = Color(android.graphics.Color.parseColor("#828282"))
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Comment,
                    contentDescription = stringResource(id = R.string.comment),
                    tint = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ){
            Row {
                Text(
                    text = "125",
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = Color(android.graphics.Color.parseColor("#828282"))
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: com.example.socialmedia.domain.models.Comment = com.example.socialmedia.domain.models.Comment(
        username = "Monica Gamage",
        comment = "Great shot! I love it"
    )
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10)),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#F6F7F9"))
        ),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = stringResource(id = R.string.profile_pic),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(32.dp)
                            .align(Alignment.Center)

                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight(700),
                        fontSize = 19.2.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = comment.comment,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 21.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "2 mins ago",
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = Color(android.graphics.Color.parseColor("#242424")),
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}