package br.com.androidtest.features.myData.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.core.design_system.components.Loading
import br.com.androidtest.core.design_system.components.LrTopAppBarNavigation
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.util.selectUserOrProfile
import br.com.androidtest.features.myData.domain.model.IconEnum
import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.components.MyDataOptionsSection
import br.com.androidtest.features.myData.presentation.components.MyDataProfileSection
import br.com.androidtest.features.myData.presentation.state.MyDataUiState

@Preview
@Composable
fun MyDataScreenPreview() {
    AppTheme {
        MyDataScreen(MyDataUiState()) {}
    }
}

@Composable
fun MyDataScreenRoot(uiState: MyDataUiState, onAction: (MyDataAction) -> Unit) {
    if (uiState.isLoading) {
        Loading()
    } else {
        MyDataScreen(uiState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDataScreen(
    uiState: MyDataUiState, onAction: (MyDataAction) -> Unit
) {
    val title = uiState.result?.screen?.title

    val profileUrl = selectUserOrProfile(
        uiState.result?.content?.user?.avatarUrl, uiState.result?.screen?.profile?.avatarUrl
    )

    val name = selectUserOrProfile(
        uiState.result?.content?.user?.name, uiState.result?.screen?.profile?.name
    )

    val cpf = selectUserOrProfile(
        uiState.result?.content?.user?.documentNumberMasked,
        uiState.result?.screen?.profile?.documentNumberMasked
    )

    val age = selectUserOrProfile(
        uiState.result?.content?.user?.age, uiState.result?.screen?.profile?.age
    )


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(br.com.androidtest.R.color.bgPrimary))
            .windowInsetsPadding(WindowInsets.systemBars), topBar = {
            LrTopAppBarNavigation(
                bgColor = colorResource(br.com.androidtest.R.color.bgBrandSolid),
                leadingIconSlot = {
                    IconButton(
                        onClick = {
                            onAction(MyDataAction.OnBackPressed)
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = stringResource(br.com.androidtest.R.string.back),
                            tint = Color.White
                        )
                    }
                })
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

                MyDataProfileSection(
                    title = title, profileUrl = profileUrl, name = name, cpf = cpf, age = age
                )

                MyDataOptionsSection(
                    options = uiState.result?.screen?.options, modifier = Modifier.fillMaxWidth()
                ) { action, url ->

                    when (action) {
                        IconEnum.DOCUMENT.key -> onAction(MyDataAction.OnMyPlanClick)
                        IconEnum.DOWNLOAD.key -> onAction(MyDataAction.OnDownloadClick)
                        IconEnum.MESSAGE.key -> onAction(MyDataAction.OnPrivacyPolicyClick(url))
                        IconEnum.BLOCK.key -> onAction(MyDataAction.OnLogoutClick)
                    }
                }
            }
        }
    }
}