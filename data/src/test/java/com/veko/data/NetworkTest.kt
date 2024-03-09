package com.veko.data

import android.content.Context
import com.veko.data.di.dataModule
import com.veko.domain.useCase.WeatherUseCase
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito.mock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NetworkTest : KoinTest {

    private val serverMock = MockWebServer()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(dataModule)
        checkModules(){
            withInstance<Context>()
        }
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mock(clazz.java)
    }

    @Before
    fun setup() {
        serverMock.apply {
            url("/")
        }
    }

    @Test
    fun Test_get_coordinates_by_city_request() = runTest {
        val repository = declareMock<WeatherUseCase> {
            givenSuspended {
                getCoords(
                    "Москва",
                    "current"
                )
            } willReturn { FileReader().readFile("success_weather_response.json") }
        }
        serverMock.dispatcher = MockWebServerDispatchers().CoordsRequestDispatcher()
        val mockResponse = serverMock.takeRequest()

        assertEquals(mockResponse.body, repository.getCoords("Москва", "current"))
    }

    @After
    fun tearDown() {
        serverMock.shutdown()
        stopKoin()
    }
}