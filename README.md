# CommonKtx

[![](https://jitpack.io/v/sasa-nori/common-ktx.svg)](https://jitpack.io/#sasa-nori/common-ktx)

## Prerequisites

* must minSdkVersion is  23 over

## install

```build.gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        
        maven { url 'https://jitpack.io' } // add
        maven { url "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven" }  // add
    }
}

```
```app/build.gradle
dependencies {
    implementation "com.github.sasa-nori:common-ktx:$latestVersion"
}
```

## Kdoc

[Document](https://sasa-nori.github.io/common-ktx/)

## Example

[CommonKtx-Example](https://github.com/sasa-nori/CommonKtx-Example)

## Tips

### Object to JSON String

Add Moshi codegen

```
kapt "com.squareup.moshi:moshi-kotlin-codegen:1.9.2"
```

Added `@JsonClass(generateAdapter = true)` to Class top

```
@JsonClass(generateAdapter = true)
data class Test(val first: String, val second: String)
```

```
Test("first", "second").toJsonString()
```

## Usage

### SingleClick

default timeout is 500ms

```
button.setOnSingleClickListener {
    // run
}
```

to custom

```
button.setOnSingleClickListener(300, { view->
     // run
})
```


### Check RuntimePermission

```
val permissions = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
if (hasPermission(permissions)) {
    // run
} else {
    requestPermissions(permissions, REQUEST_CODE)
}
```

### SharedPreferences

```
class Preferences(context: Context) : SharedPrefDelegated(context) {
    var isFirstLaunch: Boolean by pref(default = true)
}
```

```
val pref = Preferences(this)
val isFirstLaunch = when (pref.isFirstLaunch) {
    true -> {
        pref.isFirstLaunch = false
        true
    }
    else -> false
}
```

### LiveData (One Shot Event)

 
- ViewModel

```
class TestViewModel() : ViewModel {
    private var testData = MutableLiveData<Event<String>>()

    var testDataLiveData: LiveData<Event<String>>() = testData

    fun postData() {
        testData.postValue(Event("test"))
    }

}

```

- Activity

```
class TestActivity(): Activity {

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testViewModel.testDataLiveData.observer(this, EventObserver {
            Timber.d(it)
        })
    }
}

```

### SoundPool

```
class TestActivity(): Activity {

    private var soundPathList: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnSingleClickListener { view ->
            view?.let {
                SoundPool.play(soundPathList.first())
            }
        }

        lifecycleScope.launch {
            soundPathList = loadSoundPool(mutableListOf(R.raw.one))
        }
    }

}
```

### LiveData (Unit Test)

see [MainViewModelTest.kt](https://github.com/sasa-nori/common-ktx/blob/develop/example/src/test/java/net/newstyleservice/example/MainViewModelTest.kt)

```
    @Test
    fun requestApi() = runBlocking {
        // given
        val answer = mutableListOf(Shikure(0, "", "", 0, 0))
        `when`(apiService.getShikure()).thenReturn(answer)
        val liveDataTestObserver = target.getShikureList().testObserver()

        // when
        target.requestApi(apiService)

        // than
        val result = liveDataTestObserver.observedValues.first()
        assertThat(result?.getContentIfNotHandled()).isEqualTo(answer)
    }
```

# Dependency

* [Android X](https://developer.android.com/jetpack/androidx)
* [Android Support](https://developer.android.com/topic/libraries/support-library)
* [Moshi 1.9.2](https://github.com/square/moshi)
* [OkHttp 3.14.7](https://github.com/square/okhttp/)
* [Retrofit 2.8.1](https://github.com/square/retrofit)
* [Kotlin Contributions 1.3.5](https://github.com/Kotlin/kotlinx.coroutines)

# Licence

```
Copyright 2020 ss-n.app

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
