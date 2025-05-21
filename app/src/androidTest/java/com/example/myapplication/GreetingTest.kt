package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test

class GreetingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greeting_displaysCorrectText() {
        // Set the Composable under test
        composeTestRule.setContent {
            MyApplicationTheme {
                Greeting("Android")
            }
        }

        // Assert that the text "Hello Android!" is displayed
        composeTestRule.onNodeWithText("Hello Android!").assertIsDisplayed()
    }
}
