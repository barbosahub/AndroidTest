package br.com.androidtest.features.myPlan.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.Loading
import br.com.androidtest.core.design_system.components.LrTopAppBarNavigation
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.util.selectUserOrProfile
import br.com.androidtest.features.myPlan.domain.model.ExtraPlay
import br.com.androidtest.features.myPlan.presentation.action.MyPlanAction
import br.com.androidtest.features.myPlan.presentation.components.MyPlanSection
import br.com.androidtest.features.myPlan.presentation.state.MyPlanUiState

@Preview
@Composable
fun MyPlanScreenPreview() {
    AppTheme {
        MyPlanScreen(MyPlanUiState()) {}
    }
}

@Composable
fun MyPlanScreenRoot(
    uiState: MyPlanUiState,
    onAction: (MyPlanAction) -> Unit
) {
    if (uiState.isLoading) {
        Loading()
    } else {
        MyPlanScreen(uiState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlanScreen(
    uiState: MyPlanUiState, onAction: (MyPlanAction) -> Unit
) {
    val name = uiState.result?.screen?.name
    val offer = uiState.result?.screen?.offerDisplay
    val header = uiState.result?.screen?.header
    val content = uiState.result?.content

    val planValue = selectUserOrProfile(
        uiState.result?.screen?.planValue,
        uiState.result?.content?.planValue
    )

    val extraPlay = selectUserOrProfile(
        uiState.result?.screen?.extraPlay,
        ExtraPlay(
            title = stringResource(R.string.included_apps),
            options = uiState.result?.content?.extraPlay
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.bgPrimary))
            .windowInsetsPadding(WindowInsets.systemBars), topBar = {
            LrTopAppBarNavigation(
                bgColor = colorResource(R.color.bgBrandSolid),
                leadingIconSlot = {
                    IconButton(
                        onClick = {
                            onAction(MyPlanAction.OnBackPressed)
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                },
                trailingActionsSlot = {
                    IconButton(
                        onClick = {
                            onAction(MyPlanAction.OnMessageClick)
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_message),
                            contentDescription = stringResource(R.string.message),
                            tint = Color.White
                        )
                    }
                }

            )
        }) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPaddings.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space16dp)
            ) {

                MyPlanSection(
                    name = name,
                    offer = offer,
                    planValue = planValue,
                    header = header,
                    content = content,
                    extraPlay = extraPlay
                )
            }
        }
    }
}