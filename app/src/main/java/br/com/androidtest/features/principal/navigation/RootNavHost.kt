package br.com.androidtest.features.principal.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.ModalCard
import br.com.androidtest.core.design_system.components.ModalContent
import br.com.androidtest.features.myData.screen.MyDataScreenRoot
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import br.com.androidtest.features.principal.screen.PrincipalScreenRoot
import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import org.example.cmp_passwowapp.core.presentation.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel
import android.app.Activity
import androidx.compose.ui.platform.LocalContext

@Composable
fun RootNavHost() {
    val context = LocalContext.current
    val activity = context as? Activity

    val rootNavController = rememberNavController()

    Scaffold(
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.safeDrawing,
        snackbarHost = {
        }
    ) { innerPaddings ->

        NavHost(
            modifier = Modifier.padding(innerPaddings),
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

            composable<RootNavRoute.OldPlatform> {
                MyDataScreenRoot()
            }

            composable<RootNavRoute.NewPlatform> {
                MyDataScreenRoot()
            }
        }
    }
}