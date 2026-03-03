package br.com.androidtest.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavRoute {
    @Serializable
    object Main : RootNavRoute

    @Serializable
    object OldPlatform : RootNavRoute

    @Serializable
    object NewPlatform : RootNavRoute

}