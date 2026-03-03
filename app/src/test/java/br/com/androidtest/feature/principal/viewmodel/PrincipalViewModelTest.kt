package br.com.androidtest.feature.principal.viewmodel

import app.cash.turbine.test
import br.com.androidtest.base.MainDispatcherRule
import br.com.androidtest.features.principal.action.PrincipalAction
import br.com.androidtest.features.principal.event.PrincipalEvent
import br.com.androidtest.features.principal.viewmodel.PrincipalViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class PrincipalViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: PrincipalViewModel

    @Before
    fun setup() {
        viewModel = PrincipalViewModel()
    }

    @Test
    fun `onAction OnBackPressed should emit OnBackPressed event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(PrincipalAction.OnBackPressed)
            val event = awaitItem()
            assertTrue(event is PrincipalEvent.OnBackPressed)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction NewPlatformClick should emit NavigateToNewPlatformMyData event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(PrincipalAction.NewPlatformClick)
            val event = awaitItem()
            assertTrue(event is PrincipalEvent.NavigateToNewPlatformMyData)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OldPlatformClick should emit NavigateToOldPlatformMyData event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(PrincipalAction.OldPlatformClick)
            val event = awaitItem()
            assertTrue(event is PrincipalEvent.NavigateToOldPlatformMyData)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OnLogoutClick should emit LogoutAndCloseApp event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(PrincipalAction.OnLogoutClick)
            val event = awaitItem()
            assertTrue(event is PrincipalEvent.LogoutAndCloseApp)
            cancelAndIgnoreRemainingEvents()
        }
    }
}