package com.gaoyun.feature_random_advice

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaoyun.advices.common.LAUNCH_LISTEN_FOR_EFFECTS
import io.reactivex.rxjava3.core.Observable

@Composable
fun GetRandomAdviceScreenDestination(navHostController: NavHostController) {
    val viewModel: GetRandomAdviceViewModel = hiltViewModel()
    val state = viewModel.viewState.value

    GetRandomAdviceScreen(
        state = state,
        effectObservable = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) }
    )
}

@Composable
fun GetRandomAdviceScreen(
    state: GetRandomAdviceScreenContract.State,
    effectObservable: Observable<GetRandomAdviceScreenContract.Effect>,
    onEventSent: (event: GetRandomAdviceScreenContract.Event) -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectObservable.subscribe { effect ->
            when (effect) {
                is GetRandomAdviceScreenContract.Effect.AdviceLoaded -> {}
                is GetRandomAdviceScreenContract.Effect.Navigation -> {}
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            state.advice?.let {
                AdviceState(it.advice)
            }
            RefreshAdviceButton(state.isLoading) {
                onEventSent(GetRandomAdviceScreenContract.Event.GetNewAdvice)
            }
            if (state.isLoading) {
                AdviceLoader()
            }
        }
    }

}

@Composable
fun AdviceState(advice: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(text = advice, modifier = Modifier.testTag("AdviceText"))
    }
}

@Composable
fun RefreshAdviceButton(isLoading: Boolean, onRefreshClick: () -> Unit) {
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp)
        .testTag("RefreshAdviceButton")) {
        Button(onClick = { onRefreshClick() }, enabled = isLoading.not()) {
            Text(text = "Refresh")
        }
    }
}

@Composable
fun AdviceLoader() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.testTag("AdviceLoader")
        )
    }
}