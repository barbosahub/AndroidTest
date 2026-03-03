package br.com.androidtest.features.myData.di

import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.androidtest.features.myData.data.datasource.IMyDataRemoteDataSource
import br.com.androidtest.features.myData.data.datasource.MyDataRemoteDataSourceImpl
import br.com.androidtest.features.myData.data.repository.MyDataRepositoryImpl
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository
import br.com.androidtest.features.myData.presentation.viewmodel.MyDataViewModelContract
import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val myDataModule = module {

    singleOf(::MyDataRepositoryImpl).bind<IMyDataRepository>()
    singleOf(::MyDataRemoteDataSourceImpl).bind<IMyDataRemoteDataSource>()

    viewModel(qualifier = named("RW")) {
        RWMyDataViewModel(get())
    }

    viewModel(qualifier = named("NP")) {
        NPMyDataViewModel(get())
    }
}