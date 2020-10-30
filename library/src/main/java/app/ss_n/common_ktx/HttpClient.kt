package ss_n.common_ktx

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit.SECONDS

object HttpClient {
    private const val HTTP_TIME_OUT: Long = 15

    val client: OkHttpClient
        get() = customClient ?: defaultClient

    /**
     * Tips: Timeout is 10s
     */
    val defaultClient: OkHttpClient
        get() = OkHttpClient.Builder().build()

    private var customClient: OkHttpClient? = null

    fun createCustomClient(
        connectTimeout: Long = HTTP_TIME_OUT,
        writeTimeout: Long = HTTP_TIME_OUT,
        readTimeout: Long = HTTP_TIME_OUT
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(connectTimeout, SECONDS)
            .writeTimeout(writeTimeout, SECONDS)
            .readTimeout(readTimeout, SECONDS)
            .build().also {
                customClient = it
            }
    }
}
