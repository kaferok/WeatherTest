package com.veko.data

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatchers {

    companion object {
        private const val COORDS_PATH = "geo/1.0/direct?q=Москва&appid=${BuildConfig.API_KEY}"
        private const val WEATHER_MOSCOW_PATH = ""
        private const val ERROR_MESSAGE = "ERROR_NETWORK_REQUEST"
    }

    internal inner class WeatherRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                WEATHER_MOSCOW_PATH -> MockResponse().setResponseCode(200)
                    .setBody(FileReader().readFile("success_weather_response.json"))

                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class CoordsRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                COORDS_PATH -> MockResponse().setResponseCode(200)
                    .setBody(FileReader().readFile("success_coordinates_response.json.json"))

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