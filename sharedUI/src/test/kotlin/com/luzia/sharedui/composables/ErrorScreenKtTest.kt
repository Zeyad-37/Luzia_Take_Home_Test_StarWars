package com.luzia.sharedui.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luzia.sharedui.theme.LuziaStarWarsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog

@RunWith(AndroidJUnit4::class)
class ErrorScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @Test
    fun ErrorScreen_displaysMessage() {
        val errorMessage = "No internet connection"
        composeTestRule.setContent { LuziaStarWarsTheme { ErrorScreen(errorMessage) } }
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
//        composeTestRule.onNode(SemanticsMatcher()).assertIsDisplayed()

    }
}
