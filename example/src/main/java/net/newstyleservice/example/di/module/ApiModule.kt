package net.newstyleservice.example.di.module

import dagger.Module
import dagger.Provides
import net.newstyleservice.example.api.ApiService
import ss_n.common_ktx.HttpClient
import ss_n.common_ktx.extension.createRetrofitService
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return URL.createRetrofitService(
            service = ApiService::class.java,
            client = HttpClient.createCustomClient()
        )
    }

    companion object {
         const val URL = "https://mhf.ss-n.app"
    }
}
