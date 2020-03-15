# CommonKtx

[![](https://jitpack.io/v/sasa-nori/CommonKtx.svg)](https://jitpack.io/#sasa-nori/CommonKtx)

## Prerequisites

* must minSdkVersion is  23 over

## install

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation "com.github.sasa-nori:CommonKtx:$latestVersion"
}
```

## Doc

[Document markdown](./doc/library/index.md)

## Examples

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

### SingleClick

```
button.setOnSingleClickListener {
    // run
}
```

### Check RuntimePermission

```
val permissions = arrayListOf(
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

# Licence

```
Copyright 2020 NewStyleService

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