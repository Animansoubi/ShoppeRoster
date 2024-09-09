package ani.man.shopperoster.navigation

sealed class Destinations(val route: String) {
    data object Intro : Destinations(route = "Intro")

    data object SignIn : Destinations(route = "SignIn")

    data object SignUp : Destinations(route = "SignUp")
}