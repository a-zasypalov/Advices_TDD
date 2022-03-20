package com.gaoyun.feature_random_advice

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.gaoyun.advices.common.theme.AdvicesTheme
import com.gaoyun.advices.domain.model.Advice
import io.reactivex.rxjava3.core.Observable
import org.junit.Rule
import org.junit.Test

class GetRandomAdviceScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loader_should_be_shown_initially() {
        composeTestRule.setContent {
            AdvicesTheme {
                GetRandomAdviceScreen(
                    state = GetRandomAdviceScreenContract.State(null, true),
                    effectObservable = Observable.empty(),
                    onEventSent = {  }
                )
            }
        }

        composeTestRule.onNodeWithTag("AdviceLoader").assertIsDisplayed()
    }

    @Test
    fun loader_should_not_be_shown_if_advice_in_state() {
        composeTestRule.setContent {
            AdvicesTheme {
                GetRandomAdviceScreen(
                    state = GetRandomAdviceScreenContract.State(Advice(1, "Test"), false),
                    effectObservable = Observable.empty(),
                    onEventSent = {  }
                )
            }
        }

        composeTestRule.onNodeWithTag("AdviceLoader").assertDoesNotExist()
    }

    @Test
    fun advice_should_be_shown_if_it_exists_in_state() {
        composeTestRule.setContent {
            AdvicesTheme {
                GetRandomAdviceScreen(
                    state = GetRandomAdviceScreenContract.State(Advice(1, "Test"), false),
                    effectObservable = Observable.empty(),
                    onEventSent = {  }
                )
            }
        }

        composeTestRule.onNodeWithTag("AdviceText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("AdviceText").assertTextEquals("Test")
    }

}
