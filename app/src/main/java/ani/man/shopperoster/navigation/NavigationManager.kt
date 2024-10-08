package ani.man.shopperoster.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

interface NavigationManager {
    fun setUp(navHostController: NavHostController)
    fun navigateToIntro()
    fun navigateToSignInScreen()
    fun navigateToSignUpScreen()
}

class NavigationManagerImp @Inject constructor() : NavigationManager {

    private lateinit var navHostController: NavHostController
    override fun setUp(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    override fun navigateToIntro() {
       navHostController.navigate(Destinations.Intro)
    }

    override fun navigateToSignInScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToSignUpScreen() {
        TODO("Not yet implemented")
    }
}