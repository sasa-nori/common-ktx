package ss_n.common_ktx.extension

import android.location.Location

fun Location.distanceBetween(targetLocation: Location): Float {
    val result = FloatArray(3)
    Location.distanceBetween(
        latitude,
        longitude,
        targetLocation.latitude,
        targetLocation.longitude,
        result
    )
    return result[0]
}
