package co.ke.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.ke.weatherapp.ui.screens.FavouriteCitiesScreen
import co.ke.weatherapp.ui.screens.WeatherScreen
import co.ke.weatherapp.ui.utils.WeatherRoutes
import co.ke.weatherapp.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherNavHost(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel
) {

    NavHost(navController = navController, startDestination = WeatherRoutes.Weather.name) {

        composable(route = WeatherRoutes.Weather.name) {
            WeatherScreen(
                weatherViewModel = weatherViewModel,
                onDrawerItemClicked = {
                    navController.navigate(it)
                },
                onReturnToHomePageClicked = { navController.navigate(WeatherRoutes.Weather.name) })
        }

        composable(route = WeatherRoutes.FavouriteCities.name) {
            FavouriteCitiesScreen(
                onFavouriteCityClicked = {
                    weatherViewModel.getWeatherByCityName(it)
                    navController.navigate(WeatherRoutes.Weather.name)
                },
                onNavBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }

}