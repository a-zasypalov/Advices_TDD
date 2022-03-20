package com.gaoyun.feature_random_advice

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
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

    @Test
    fun refresh_button_should_be_active_if_advice_loaded() {
        composeTestRule.setContent {
            AdvicesTheme {
                GetRandomAdviceScreen(
                    state = GetRandomAdviceScreenContract.State(Advice(1, "Test"), false),
                    effectObservable = Observable.empty(),
                    onEventSent = {  }
                )
            }
        }

        composeTestRule.onNodeWithTag("RefreshAdviceButton").assertIsEnabled()
    }

    @Test
    fun refresh_button_should_send_refresh_event_on_click() {
        composeTestRule.setContent {
            AdvicesTheme {
                GetRandomAdviceScreen(
                    state = GetRandomAdviceScreenContract.State(Advice(1, "Test"), false),
                    effectObservable = Observable.empty(),
                    onEventSent = {
                        assert(it is GetRandomAdviceScreenContract.Event.GetNewAdvice)
                    }
                )
            }
        }

        composeTestRule.onNodeWithTag("RefreshAdviceButton").performClick()
    }

}
