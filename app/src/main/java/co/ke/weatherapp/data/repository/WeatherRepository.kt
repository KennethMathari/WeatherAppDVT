package co.ke.weatherapp.data.repository

import co.ke.weatherapp.data.network.dto.CurrentWeather
import co.ke.weatherapp.data.network.dto.WeatherForecast
import co.ke.weatherapp.data.network.utils.NetworkResult
import co.ke.weatherapp.ui.state.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: String, longitude: String, apiKey: String
    ): Flow<NetworkResult<CurrentWeather>>

    suspend fun getWeatherForecast(
        latitude: String, longitude: String, apiKey: String
    ): Flow<NetworkResult<WeatherForecast>>

    suspend fun getWeatherByCityName(
        cityName: String, apiKey: String
    ): Flow<NetworkResult<WeatherInfo>>
}