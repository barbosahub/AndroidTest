package br.com.androidtest.feature.myPlan.viewmodel

import app.cash.turbine.test
import br.com.androidtest.base.MainDispatcherRule
import br.com.androidtest.feature.myPlan.repository.MyPlanRepositoryTest
import br.com.androidtest.features.myPlan.presentation.action.MyPlanAction
import br.com.androidtest.features.myPlan.presentation.event.MyPlanEvent
import br.com.androidtest.features.myPlan.presentation.viewmodel.RWMyPlanViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RWMyPlanViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: MyPlanRepositoryTest
    private lateinit var viewModel: RWMyPlanViewModel

    @Before
    fun setup() {
        repository = MyPlanRepositoryTest()
        viewModel = RWMyPlanViewModel(repository)
    }

    @Test
    fun `fetchMyPlanRW should return result successfully`() = runTest {
        repository.shouldReturnError = false
        val viewModel = RWMyPlanViewModel(repository)

        viewModel.state.test {
            val initial = awaitItem()
            Assert.assertFalse(initial.isLoading)
            Assert.assertNull(initial.result)

            val loading = awaitItem()
            Assert.assertTrue(loading.isLoading)

            val success = awaitItem()
            Assert.assertFalse(success.isLoading)
            Assert.assertNotNull(success.result)
            Assert.assertEquals("R$ 39,99", success.result?.content?.planValue)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchMyPlanRW should handle error properly`() = runTest {
        repository.shouldReturnError = true

        viewModel.state.test {
            val loadingState = awaitItem()
            Assert.assertTrue(loadingState.isLoading)
            Assert.assertNull(loadingState.result)

            val errorState = awaitItem()
            Assert.assertFalse(errorState.isLoading)
            Assert.assertNull(errorState.result)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `onAction OnBackPressed should send OnBackPressed event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyPlanAction.OnBackPressed)
            val event = awaitItem()
            Assert.assertTrue(event is MyPlanEvent.OnBackPressed)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction OnMessageClick should not emit event`() = runTest {
        viewModel.event.test {
            viewModel.onAction(MyPlanAction.OnMessageClick)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `state should transition from loading to success`() = runTest {
        repository.shouldReturnError = false

        viewModel.state.test {
            val loadingState = awaitItem()
            Assert.assertTrue(loadingState.isLoading)
            Assert.assertNull(loadingState.result)

            val successState = awaitItem()
            Assert.assertFalse(successState.isLoading)
            Assert.assertNotNull(successState.result)

            val result = successState.result!!
            Assert.assertEquals("R$ 39,99", result.content?.planValue)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `state should transition from loading to error`() = runTest {
        repository.shouldReturnError = true
        val viewModel = RWMyPlanViewModel(repository)

        viewModel.state.test {
            val initial = awaitItem()
            Assert.assertFalse(initial.isLoading)
            Assert.assertNull(initial.result)

            val loading = awaitItem()
            Assert.assertTrue(loading.isLoading)

            val errorState = awaitItem()
            Assert.assertFalse(errorState.isLoading)
            Assert.assertNull(errorState.result)

            cancelAndIgnoreRemainingEvents()
        }
    }
}