package com.example.socialmedia

import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.util.TestTags.PASSWORD_TOGGLE
import com.example.socialmedia.presentation.util.TestTags.STANDARD_TEXT_FIELD
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalPagerApi
@RunWith(AndroidJUnit4::class)
class StandardTextFieldsTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun enterTooLongString_maxLengthNotExceeded() {
        composeTestRule.activity.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 5
                )
            }
        }
        val expectedString = "aaaaa"
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()
       composeTestRule
           .onNodeWithTag(STANDARD_TEXT_FIELD)
           .performTextInput(expectedString)
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("a")
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .assertTextEquals(expectedString)
    }

    @Test
    fun enterPassword_toggleVisibility_passwordVisible() {
        composeTestRule.activity.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    maxLength = 5,
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .semantics {
                            testTag = STANDARD_TEXT_FIELD
                        }
                )
            }
        }
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("aaaaa")

        composeTestRule
            .onNodeWithTag(PASSWORD_TOGGLE)

    }
}