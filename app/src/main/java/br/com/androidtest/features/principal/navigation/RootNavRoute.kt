package br.com.androidtest.features.principal.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavRoute {

    @Serializable
    object Main : RootNavRoute

    @Serializable
    object OldPlatformMyData : RootNavRoute

    @Serializable
    object NewPlatformMyData : RootNavRoute

    @Serializable
    object OldPlatformMyPlan : RootNavRoute

    @Serializable
    object NewPlatformMyPlan : RootNavRoute

}