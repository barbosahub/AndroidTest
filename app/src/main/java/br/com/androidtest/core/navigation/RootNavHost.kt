package br.com.androidtest.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RootNavHost() {
    val rootNavController = rememberNavController()


    NavHost(
        startDestination = RootNavRoute.Main,
        navController = rootNavController
    ) {
        composable<RootNavRoute.Main> {

        }

        composable<RootNavRoute.OldPlatform> {

        }

        composable<RootNavRoute.NewPlatform> {

        }
    }
}