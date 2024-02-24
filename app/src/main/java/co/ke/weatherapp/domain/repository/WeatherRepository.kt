package co.ke.weatherapp.domain.repository

import co.ke.weatherapp.data.network.dto.CurrentWeather
import co.ke.weatherapp.data.network.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: String, longitude:String, apiKey: String)
            : Flow<NetworkResult<CurrentWeather>>
}