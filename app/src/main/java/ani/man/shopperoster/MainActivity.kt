package ani.man.shopperoster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ani.man.shopperoster.navigation.NavigationGraph
import ani.man.shopperoster.navigation.NavigationManager
import ani.man.shopperoster.ui.theme.ShoppeRosterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppeRosterTheme {
                val navController = rememberNavController()
                navigationManager.setUp(navController)
                NavigationGraph(navController = navController)
            }
        }
    }
}