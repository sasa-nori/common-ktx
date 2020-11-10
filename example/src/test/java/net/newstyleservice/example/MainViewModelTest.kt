package net.newstyleservice.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import ss_n.common_ktx.extension.testObserver


@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Mock
    lateinit var apiService: ApiService

    @InjectMocks
    lateinit var target: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestApi() = runBlocking {
        // given
        val answer = mutableListOf(Shikure(0, "", "", 0, 0))
        `when`(apiService.getShikure()).thenReturn(answer)
        val liveDataTestObserver = target.getShikureList().testObserver()

        // when
        target.requestApi(apiService)

        // than
        val result = liveDataTestObserver.observedValues.first()
        assertThat(result?.getContentIfNotHandled()).isEqualTo(answer)
    }
}