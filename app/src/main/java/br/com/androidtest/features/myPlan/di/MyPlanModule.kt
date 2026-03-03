package br.com.androidtest.features.myPlan.di

import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import br.com.androidtest.features.myPlan.data.datasource.IMyPlanRemoteDataSource
import br.com.androidtest.features.myPlan.data.datasource.MyPlanRemoteDataSourceImpl
import br.com.androidtest.features.myPlan.data.repository.MyPlanRepositoryImpl
import br.com.androidtest.features.myPlan.domain.repository.IMyPlanRepository
import br.com.androidtest.features.myPlan.presentation.viewmodel.NPMyPlanViewModel
import br.com.androidtest.features.myPlan.presentation.viewmodel.RWMyPlanViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val myPlanModule = module {

    singleOf(::MyPlanRepositoryImpl).bind<IMyPlanRepository>()
    singleOf(::MyPlanRemoteDataSourceImpl).bind<IMyPlanRemoteDataSource>()

    viewModel(qualifier = named("RW")) {
        RWMyPlanViewModel(get())
    }

    viewModel(qualifier = named("NP")) {
        NPMyPlanViewModel(get())
    }
}