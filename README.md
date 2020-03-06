# CommonKtx

[![](https://jitpack.io/v/sasa-nori/CommonKtx.svg)](https://jitpack.io/#sasa-nori/CommonKtx)
[![Build Status](https://app.bitrise.io/app/22d11ce72703f6cf/status.svg?token=pUw0yloA1oUm8sWmo03Deg&branch=develop)](https://app.bitrise.io/app/22d11ce72703f6cf)

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

## Examples

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