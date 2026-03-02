package br.com.androidtest.core.di


import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    // --- Data ---

//    single {
//        HttpClientFactory.create(
//            engine = get(),
//            userSessionRepository = lazy { get() }
//        )
//    }

//    factory<IAppPermissionRepository> { (controller: PermissionsController) ->
//        AppPermissionRepositoryImpl(controller)
//    }
//    singleOf(::AuthTokenCacheManager).bind<IAuthTokenCacheManager>()
//    singleOf(::AuthTokenCacheManager).bind<IAuthTokenCacheManager>()
//
//    // DataSources
//    singleOf(::AppPreferencesLocalDataSource).bind<IAppPreferencesLocalDataSource>()
//    singleOf(::DeviceLocationLocalDataSource).bind<IDeviceLocationLocalDataSource>()
//    singleOf(::AppVersionRemoteDataSource).bind<IAppVersionRemoteDataSource>()
//    singleOf(::TokenLocalDataSource).bind<ITokenLocalDataSource>()
//    singleOf(::UserSessionLocalDataSource).bind<IUserSessionLocalDataSource>()
//
//    // Repositories
//    singleOf(::AppPreferencesRepositoryImpl).bind<IAppPreferencesRepository>()
//    singleOf(::AppVersionRepositoryImpl).bind<IAppVersionRepository>()
//    singleOf(::TokenRepositoryImpl).bind<ITokenRepository>()
//    singleOf(::DeviceLocationRepositoryImpl).bind<IDeviceLocationRepository>()
//    singleOf(::UserSessionRepositoryImpl).bind<IUserSessionRepository>()
//

//    // --- Presentation ---
//    viewModelOf(::UserSessionViewModel)
//    viewModelOf(::AppNavHostTrackerViewModel)
    viewModelOf(::PrincipalViewModel)


}