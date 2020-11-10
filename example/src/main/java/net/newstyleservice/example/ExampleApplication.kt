package net.newstyleservice.example

import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.newstyleservice.example.di.component.ApplicationComponent
import net.newstyleservice.example.di.component.DaggerApplicationComponent
import javax.inject.Inject

class ExampleApplication : MultiDexApplication(), HasAndroidInjector {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
        instance = this
    }

    override fun androidInjector(): AndroidInjector<Any?>? {
        return androidInjector
    }

    companion object {
        @JvmStatic
        lateinit var instance: ExampleApplication
            private set
    }
}
