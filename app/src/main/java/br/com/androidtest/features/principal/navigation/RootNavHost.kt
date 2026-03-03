package br.com.androidtest.features.principal.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.ModalCard
import br.com.androidtest.core.design_system.components.ModalContent
import br.com.androidtest.core.util.ObserveAsEvents
import br.com.androidtest.core.util.sharePdfFromAssets
import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.event.MyDataEvent
import br.com.androidtest.features.myData.presentation.screen.MyDataScreenRoot
import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import br.com.androidtest.features.myPlan.presentation.event.MyPlanEvent
import br.com.androidtest.features.myPlan.presentation.screen.MyPlanScreenRoot
import br.com.androidtest.features.myPlan.presentation.viewmodel.NPMyPlanViewModel
import br.com.androidtest.features.myPlan.presentation.viewmodel.RWMyPlanViewModel
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import br.com.androidtest.features.principal.screen.PrincipalScreenRoot
import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun RootNavHost() {
    val context = LocalContext.current
    val activity = context as? Activity

    val rootNavController = rememberNavController()


    NavHost(
        startDestination = RootNavRoute.Main,
        navController = rootNavController
    ) {
        composable<RootNavRoute.Main> {
            val viewModel: PrincipalViewModel = koinViewModel()
            val onAction = viewModel::onAction

            val showBottomSheet = remember { mutableStateOf(false) }

            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    PrincipalEvent.OnBackPressed -> {
                        showBottomSheet.value = !showBottomSheet.value
                    }

                    PrincipalEvent.NavigateToNewPlatformMyData -> {
                        rootNavController.navigate(RootNavRoute.NewPlatformMyData)
                    }

                    PrincipalEvent.NavigateToOldPlatformMyData -> {
                        rootNavController.navigate(RootNavRoute.OldPlatformMyData)
                    }

                    PrincipalEvent.LogoutAndCloseApp -> {
                        activity?.finishAffinity()
                    }
                }
            }

            ModalCard(
                showDialog = showBottomSheet.value,
                onDismiss = {
                    showBottomSheet.value = false
                }, content = {
                    ModalContent(
                        primaryText = stringResource(R.string.logout_question),
                        secondaryText = stringResource(R.string.do_you_really_want_to_log_out),
                        onConfirm = {
                            showBottomSheet.value = false
                            onAction(PrincipalAction.OnLogoutClick)
                        },
                        onCancel = {
                            showBottomSheet.value = false
                        }
                    )
                })

            PrincipalScreenRoot(
                onAction
            )
        }

        composable<RootNavRoute.NewPlatformMyData> {
            val viewModel: NPMyDataViewModel = koinViewModel(qualifier = named("NP"))

            val uiState = viewModel.state.collectAsStateWithLifecycle().value
            val onAction = viewModel::onAction

            val showBottomSheet = remember { mutableStateOf(false) }

            ModalCard(
                showDialog = showBottomSheet.value,
                onDismiss = {
                    showBottomSheet.value = false
                }, content = {
                    // Its hardcoded but you can get the option text by ui state.
                    ModalContent(
                        primaryText = stringResource(R.string.logout_question),
                        secondaryText = stringResource(R.string.do_you_really_want_to_log_out),
                        onConfirm = {
                            showBottomSheet.value = false
                            onAction(MyDataAction.LogoutAndCloseApp)
                        },
                        onCancel = {
                            showBottomSheet.value = false
                        }
                    )
                })


            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    MyDataEvent.OnBackPressed -> rootNavController.popBackStack()

                    MyDataEvent.Download -> {
                        context.sharePdfFromAssets("terms.pdf")
                    }

                    MyDataEvent.NavigateToMyPlan -> {
                        rootNavController.navigate(RootNavRoute.NewPlatformMyPlan)
                    }

                    is MyDataEvent.NavigateToPrivacyPolicy -> context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            event.url?.toUri()
                        )
                    )

                    MyDataEvent.LogoutAndCloseApp -> {
                        activity?.finishAffinity()
                    }

                    MyDataEvent.ShowLogout -> {
                        showBottomSheet.value = !showBottomSheet.value
                    }
                }
            }

            MyDataScreenRoot(uiState, onAction)
        }

        composable<RootNavRoute.OldPlatformMyData> {
            val viewModel: RWMyDataViewModel = koinViewModel(qualifier = named("RW"))

            val uiState = viewModel.state.collectAsStateWithLifecycle().value
            val onAction = viewModel::onAction
            val showBottomSheet = remember { mutableStateOf(false) }

            ModalCard(
                showDialog = showBottomSheet.value,
                onDismiss = {
                    showBottomSheet.value = false
                }, content = {
                    // Its hardcoded but you can get the option text by ui state.
                    ModalContent(
                        primaryText = stringResource(R.string.logout_question),
                        secondaryText = stringResource(R.string.do_you_really_want_to_log_out),
                        onConfirm = {
                            showBottomSheet.value = false
                            onAction(MyDataAction.LogoutAndCloseApp)
                        },
                        onCancel = {
                            showBottomSheet.value = false
                        }
                    )
                })

            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    MyDataEvent.OnBackPressed -> {
                        rootNavController.popBackStack()
                    }

                    MyDataEvent.Download -> {
                        context.sharePdfFromAssets("terms.pdf")
                    }

                    MyDataEvent.NavigateToMyPlan -> {
                        rootNavController.navigate(RootNavRoute.OldPlatformMyPlan)
                    }

                    MyDataEvent.LogoutAndCloseApp -> {
                        activity?.finishAffinity()
                    }

                    MyDataEvent.ShowLogout -> {
                        showBottomSheet.value = !showBottomSheet.value
                    }

                    is MyDataEvent.NavigateToPrivacyPolicy -> {}
                }
            }

            MyDataScreenRoot(uiState, onAction)
        }

        composable<RootNavRoute.NewPlatformMyPlan> {
            val viewModel: NPMyPlanViewModel = koinViewModel(qualifier = named("NP"))
            val uiState = viewModel.state.collectAsStateWithLifecycle().value
            val onAction = viewModel::onAction

            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    MyPlanEvent.OnBackPressed -> {
                        rootNavController.popBackStack()
                    }
                }
            }

            MyPlanScreenRoot(uiState, onAction)
        }


        composable<RootNavRoute.OldPlatformMyPlan> {
            val viewModel: RWMyPlanViewModel = koinViewModel(qualifier = named("RW"))
            val uiState = viewModel.state.collectAsStateWithLifecycle().value
            val onAction = viewModel::onAction

            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    MyPlanEvent.OnBackPressed -> {
                        rootNavController.popBackStack()
                    }
                }
            }

            MyPlanScreenRoot(uiState, onAction)
        }
    }
}