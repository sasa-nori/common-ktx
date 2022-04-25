//[library](../../index.md)/[ss_n.common_ktx.extension](index.md)/[requestLocation](request-location.md)

# requestLocation

[androidJvm]\
fun [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[requestLocation](request-location.md)(locationListener: [LocationListener](https://developer.android.com/reference/kotlin/android/location/LocationListener.html), permissionError: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?? = null, criteria: [Criteria](https://developer.android.com/reference/kotlin/android/location/Criteria.html) = Criteria().apply
    {
        accuracy = Criteria.ACCURACY_FINE
        powerRequirement = Criteria.POWER_HIGH
        isSpeedRequired = false
        isAltitudeRequired = false
        isBearingRequired = false
        isCostAllowed = true
        horizontalAccuracy = Criteria.ACCURACY_HIGH
        verticalAccuracy = Criteria.ACCURACY_HIGH
    }, enabledOnly: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, minTime: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000, minDistance: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 40f)
