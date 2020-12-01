package net.newstyleservice.example.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.newstyleservice.example.ExampleApplication
import net.newstyleservice.example.di.module.ActivityModule
import net.newstyleservice.example.di.module.ApiModule
import net.newstyleservice.example.di.module.ViewModelFactoryModule
import net.newstyleservice.example.di.module.ViewModelModule
import net.newstyleservice.example.view.InjectActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ApiModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ExampleApplication> {

    fun inject(injectActivity: InjectActivity)
}