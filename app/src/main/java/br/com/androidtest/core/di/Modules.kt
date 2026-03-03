package br.com.androidtest.core.di

import br.com.androidtest.features.myData.di.myDataModule
import br.com.androidtest.features.myPlan.di.myPlanModule

val sharedModules = listOf(
    coreModule, myDataModule,myPlanModule
)