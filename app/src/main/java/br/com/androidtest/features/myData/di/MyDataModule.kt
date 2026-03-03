package br.com.androidtest.features.myData.di


import br.com.androidtest.features.myData.data.datasource.IMyDataRemoteDataSource
import br.com.androidtest.features.myData.data.datasource.MyDataRemoteDataSourceImpl
import br.com.androidtest.features.myData.data.repository.MyDataRepositoryImpl
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository
import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val myDataModule = module {
    singleOf(::MyDataRepositoryImpl).bind<IMyDataRepository>()
    singleOf(::MyDataRemoteDataSourceImpl).bind<IMyDataRemoteDataSource>()

    viewModelOf(::NPMyDataViewModel)
    viewModelOf(::RWMyDataViewModel)
}