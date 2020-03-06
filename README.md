# CommonKtx

[![](https://jitpack.io/v/sasa-nori/CommonKtx.svg)](https://jitpack.io/#sasa-nori/CommonKtx)

## install

```
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