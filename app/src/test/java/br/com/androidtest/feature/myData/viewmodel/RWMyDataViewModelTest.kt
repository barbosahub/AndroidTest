package br.com.androidtest.feature.myData.viewmodel

import app.cash.turbine.test
import br.com.androidtest.base.MainDispatcherRule
import br.com.androidtest.feature.myData.repository.MyDataRepositoryTest
import br.com.androidtest.features.myData.presentation.action.MyDataAction
import br.com.androidtest.features.myData.presentation.event.MyDataEvent
import br.com.androidtest.features.myData.presentation.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.myData.presentation.viewmodel.RWMyDataViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RWMyDataViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: MyDataRepositoryTest
    private lateinit var viewModel: RWMyDataViewModel

    @Before
    fun setup() {
        repository = MyDataRepositoryTest()
        viewModel = RWMyDataViewModel(repository)
    }


    @Test
    fun `fetchMyDataRW should return result successfully`() = runTest {
        repository.shouldReturnError = false
        val viewModel = RWMyDataViewModel(repository)

        viewModel.state.test {
            // 1️⃣ Estado inicial do StateFlow
            val initial = awaitItem()
            assertFalse(initial.isLoading) // <- false, não true
            assertNull(initial.result)

            // 2️⃣ Estado de loading
            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertNull(loading.result)

            // 3️⃣ Estado de sucesso após fetch
            val success = awaitItem()
            assertFalse(success.isLoading)
            assertNotNull(success.result)
            assertEquals("Rui Barbosa", success.result?.content?.user?.name)

            cancelAndIgnoreRemainingEvents()
        }
    }

    // ======================
    // Teste de erro
    // ======================
    @Test
    fun `fetchMyDataRW should handle error properly`() = runTest {
        repository.shouldReturnError = true
        val viewModel = RWMyDataViewModel(repository)

        viewModel.state.test {
            // 1️⃣ Estado inicial do StateFlow
            val initial = awaitItem()
            assertFalse(initial.isLoading) // <- false, não true
            assertNull(initial.result)

            // 2️⃣ Estado de loading (fetch iniciado)
            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertNull(loading.result)

            // 3️⃣ Estado de erro (fetch falhou)
            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertNull(errorState.result)

            cancelAndIgnoreRemainingEvents()
        }
    }

    // ======================
    // Teste de eventos
    // ======================
    @Test
    fun `onAction OnBackPressed should send OnBackPressed event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyDataAction.OnBackPressed)
            val event = awaitItem()
            assertTrue(event is MyDataEvent.OnBackPressed)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OnDownloadClick should send Download event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyDataAction.OnDownloadClick)
            val event = awaitItem()
            assertTrue(event is MyDataEvent.Download)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OnLogoutClick should send ShowLogout event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyDataAction.OnLogoutClick)
            val event = awaitItem()
            assertTrue(event is MyDataEvent.ShowLogout)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OnMyPlanClick should send NavigateToMyPlan event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyDataAction.OnMyPlanClick)
            val event = awaitItem()
            assertTrue(event is MyDataEvent.NavigateToMyPlan)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction LogoutAndCloseApp should send LogoutAndCloseApp event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyDataAction.LogoutAndCloseApp)
            val event = awaitItem()
            assertTrue(event is MyDataEvent.LogoutAndCloseApp)
            cancelAndIgnoreRemainingEvents()
        }
    }
}