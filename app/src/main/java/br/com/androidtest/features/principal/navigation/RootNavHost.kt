package br.com.androidtest.features.principal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.ModalCard
import br.com.androidtest.core.design_system.components.ModalContent
import br.com.androidtest.features.myData.presentation.screen.MyDataScreenRoot
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import br.com.androidtest.features.principal.screen.PrincipalScreenRoot
import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import br.com.androidtest.core.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.event.MyDataEvent
import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import androidx.core.net.toUri

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

                    PrincipalEvent.NavigateToNewPlatform -> {
                        rootNavController.navigate(RootNavRoute.NewPlatform)
                    }

                    PrincipalEvent.NavigateToOldPlatform -> {
                        rootNavController.navigate(RootNavRoute.OldPlatform)
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

        composable<RootNavRoute.NewPlatform> {
            val viewModel: NPMyDataViewModel = koinViewModel()
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

                    }

                    MyDataEvent.NavigateToMyPlan -> {

                    }

                    is MyDataEvent.NavigateToPrivacyPolicy -> {
                        context.startActivity(Intent(Intent.ACTION_VIEW, event.url?.toUri()))
                    }

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

        composable<RootNavRoute.OldPlatform> {
            val viewModel: RWMyDataViewModel = koinViewModel()
            val uiState = viewModel.state.collectAsStateWithLifecycle().value
            val onAction = viewModel::onAction

            ObserveAsEvents(viewModel.event) { event ->
                when (event) {
                    MyDataEvent.OnBackPressed -> {
                        rootNavController.popBackStack()
                    }

                    else -> {}
                }
            }
            MyDataScreenRoot(uiState, onAction)
        }
    }
}