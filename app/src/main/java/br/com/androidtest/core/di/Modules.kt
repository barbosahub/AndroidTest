package br.com.androidtest.core.di

import br.com.androidtest.features.myData.di.myDataModule

val sharedModules = listOf(
    coreModule, myDataModule
)