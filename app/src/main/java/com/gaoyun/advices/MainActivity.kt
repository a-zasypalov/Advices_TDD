package com.gaoyun.advices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gaoyun.advices.common.AdvicesNavigation
import com.gaoyun.advices.common.theme.AdvicesTheme
import com.gaoyun.feature_random_advice.GetRandomAdviceScreenDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdvicesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    GlobalDestinationState()
                }
            }
        }
    }

}

/**
 * Global navigator state widget
 */
@Composable
fun GlobalDestinationState() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = AdvicesNavigation.Route.RANDOM_ADVICE) {

        composable(AdvicesNavigation.Route.RANDOM_ADVICE) { GetRandomAdviceScreenDestination(navHostController = navHostController) }

    }
}