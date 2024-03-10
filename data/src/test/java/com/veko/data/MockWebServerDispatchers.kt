package com.veko.data

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatchers {

    companion object {
        private const val COORDS_PATH = "/data/3.0/onecall?q=Moscow&appid=${BuildConfig.API_KEY}"
        private const val WEATHER_MOSCOW_PATH = "/data/3.0/onecall?lat=55.7504&lon=37.6175&exclude=current&lang=ru&appid=${BuildConfig.API_KEY}"
        private const val ERROR_MESSAGE = "ERROR_NETWORK_REQUEST"
    }

    internal inner class WeatherRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                WEATHER_MOSCOW_PATH -> MockResponse().setResponseCode(200)
                    .setBody(FileReader.readFile("success_weather_response.json"))

                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class CoordsRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                COORDS_PATH -> {
                    MockResponse().setResponseCode(200)
                        .setBody(FileReader.readFile("success_coordinates_response.json"))
                }

                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
                .setBody(ERROR_MESSAGE)
        }
    }
}