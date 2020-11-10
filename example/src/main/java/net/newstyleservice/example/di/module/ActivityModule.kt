package net.newstyleservice.example.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.newstyleservice.example.view.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun mainActivity(): MainActivity
}
