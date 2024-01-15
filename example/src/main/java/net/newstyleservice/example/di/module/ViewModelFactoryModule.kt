package net.newstyleservice.example.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import net.newstyleservice.example.di.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
        ViewModelProvider.Factory
}