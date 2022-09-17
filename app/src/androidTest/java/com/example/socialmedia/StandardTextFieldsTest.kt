package com.example.socialmedia

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.login.LoginScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalPagerApi
@RunWith(AndroidJUnit4::class)
class StandardTextFieldsTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){

    }
    @Test
    fun enterTooLongString_maxLengthNotExceeded() {
        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 25
                )
            }
        }
       composeTestRule
           .onNodeWithTag("standard_text_field")
           .performTextInput("123456")
        composeTestRule
            .onNodeWithTag("standard_text_field")
            .assertTextContains("123456")
    }
}