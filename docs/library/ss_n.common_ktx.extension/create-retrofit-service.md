//[library](../../index.md)/[ss_n.common_ktx.extension](index.md)/[createRetrofitService](create-retrofit-service.md)

# createRetrofitService

[androidJvm]\
fun &lt;[T](create-retrofit-service.md)&gt; [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[createRetrofitService](create-retrofit-service.md)(service: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](create-retrofit-service.md)&gt;, client: OkHttpClient? = HttpClient.defaultClient, converterFactory: Converter.Factory? = MoshiConverterFactory.create(
        Builder().add(KotlinJsonAdapterFactory()).build()
    )): [T](create-retrofit-service.md)

Create Retrofit Service

#### Return

Service interface class Instance

## Parameters

androidJvm

| | |
|---|---|
| T | Service interface class |
| service | Service interface class |
| client | OkHttpClient :default [HttpClient](../ss_n.common_ktx/-http-client/index.md) |
| converterFactory | Json Parser Converter Factory :default MoshiConverterFactory |
