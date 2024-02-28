package co.ke.weatherapp.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import co.ke.weatherapp.data.network.dto.CurrentWeather
import co.ke.weatherapp.data.network.dto.WeatherForecast
import co.ke.weatherapp.data.network.services.WeatherApi
import co.ke.weatherapp.data.network.utils.NetworkResult
import co.ke.weatherapp.data.network.utils.safeApiCall
import co.ke.weatherapp.di.IoDispatcher
import co.ke.weatherapp.domain.mappers.mapToCurrentWeatherDomain
import co.ke.weatherapp.domain.mappers.mapToWeatherForecastDomain
import co.ke.weatherapp.domain.model.CurrentWeatherDomain
import co.ke.weatherapp.domain.model.WeatherForecastDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: String, longitude: String, apiKey: String
    ): Flow<NetworkResult<CurrentWeatherDomain>> {
        return flow {
            emit(NetworkResult.Loading)
            val result = safeApiCall {
                weatherApi.getCurrentWeather(
                    latitude, longitude, apiKey
                )
            }
            val mappedResult = mapToCurrentWeatherDomain(result)
            emit(mappedResult)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getWeatherForecast(
        latitude: String, longitude: String, apiKey: String
    ): Flow<NetworkResult<WeatherForecastDomain>> {
        return flow {
            val result = safeApiCall {
                weatherApi.getWeatherForecast(
                    latitude, longitude, apiKey
                )
            }
            val mappedResult = mapToWeatherForecastDomain(result)
            emit(mappedResult)

        }.flowOn(ioDispatcher).onStart {
            emit(NetworkResult.Loading)
        }
    }
}