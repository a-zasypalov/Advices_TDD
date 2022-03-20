package com.gaoyun.feature_random_advice

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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


}
