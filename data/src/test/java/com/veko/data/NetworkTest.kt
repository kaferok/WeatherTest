package com.veko.data

import com.veko.data.api.WeatherApi
import com.veko.data.retorift.ApiKeyInterceptor
import com.veko.data.retorift.RetrofitBuilder
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.koin.core.context.GlobalContext.stopKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NetworkTest {

    private val serverMock = MockWebServer()

    private val weatherApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(serverMock.url("/"))
            .client(
                RetrofitBuilder.buildHttpClient(
                    ApiKeyInterceptor(),
                    RetrofitBuilder.getLoggingInterceptor()
                ).build()
            )
            .build()
            .create(WeatherApi::class.java)
    }

    @Test
    fun Test_get_coordinates_by_city_request() = runTest {
        serverMock.dispatcher = MockWebServerDispatchers().CoordsRequestDispatcher()

        weatherApi.getCoordsByCity(city = "Moscow")

        val request = serverMock.takeRequest(3000L, TimeUnit.MILLISECONDS)
        checkRequest(request)
    }

    @Test
    fun Test_get_weather() = runTest {
        serverMock.dispatcher = MockWebServerDispatchers().WeatherRequestDispatcher()

        weatherApi.getWeather(lat = 55.7504, lon = 37.6175, exclude = "current")

        val request = serverMock.takeRequest(3000L, TimeUnit.MILLISECONDS)
        checkRequest(request)
    }

    private fun checkRequest(request: RecordedRequest?) {
        assertTrue(request != null)
        assertNotEquals(request?.requestUrl, null)
        assertEquals(request?.requestUrl?.queryParameter("appid"), BuildConfig.API_KEY)
    }

    @After
    fun tearDown() {
        serverMock.shutdown()
        stopKoin()
    }
}