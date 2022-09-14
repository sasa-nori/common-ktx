package ss_n.common_ktx

import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import java.util.concurrent.TimeUnit.SECONDS

object HttpClient {
    private const val HTTP_TIME_OUT: Long = 15

    val client: OkHttpClient
        get() = customClient ?: defaultClient

    /**
     * Tips: Timeout is 10s
     */
    val defaultClient: OkHttpClient
        get() = Builder().build()

    private var customClient: OkHttpClient? = null

    fun createCustomClient(
        connectTimeout: Long = HTTP_TIME_OUT,
        writeTimeout: Long = HTTP_TIME_OUT,
        readTimeout: Long = HTTP_TIME_OUT,
        isShowLog: Boolean = true,
        logLevel: Level = BODY
    ): OkHttpClient {
        val builder = Builder()
            .connectTimeout(connectTimeout, SECONDS)
            .writeTimeout(writeTimeout, SECONDS)
            .readTimeout(readTimeout, SECONDS)
        if (isShowLog) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = logLevel
            })
        }
        return builder.build().also {
            customClient = it
        }
    }
}
