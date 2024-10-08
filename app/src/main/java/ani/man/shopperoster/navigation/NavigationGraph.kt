package ani.man.shopperoster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ani.man.shopperoster.intro.IntroScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Intro.route,
    ) {
        
        composable(Destinations.Intro.route) {
            IntroScreen()
        }

        composable(Destinations.SignIn.route) {

        }

        composable(route = Destinations.SignUp.route) {

        }
    }
}