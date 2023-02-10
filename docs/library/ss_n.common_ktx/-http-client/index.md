//[library](../../../index.md)/[ss_n.common_ktx](../index.md)/[HttpClient](index.md)

# HttpClient

[androidJvm]\
object [HttpClient](index.md)

## Functions

| Name | Summary |
|---|---|
| [createCustomClient](create-custom-client.md) | [androidJvm]<br>fun [createCustomClient](create-custom-client.md)(connectTimeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = HTTP_TIME_OUT, writeTimeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = HTTP_TIME_OUT, readTimeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = HTTP_TIME_OUT, isShowLog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, logLevel: HttpLoggingInterceptor.Level = BODY): OkHttpClient |

## Properties

| Name | Summary |
|---|---|
| [client](client.md) | [androidJvm]<br>val [client](client.md): OkHttpClient |
| [defaultClient](default-client.md) | [androidJvm]<br>val [defaultClient](default-client.md): OkHttpClient<br>Tips: Timeout is 10s |
