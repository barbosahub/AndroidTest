package br.com.androidtest.core.di


import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val coreModule = module {
    viewModelOf(::PrincipalViewModel)

}