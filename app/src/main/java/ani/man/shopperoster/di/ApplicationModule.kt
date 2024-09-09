package ani.man.shopperoster.di

import ani.man.shopperoster.navigation.NavigationManager
import ani.man.shopperoster.navigation.NavigationManagerImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager {
        return NavigationManagerImp()
    }
}