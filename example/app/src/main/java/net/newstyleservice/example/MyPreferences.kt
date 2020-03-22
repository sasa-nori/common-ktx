package net.newstyleservice.example

import android.content.Context
import net.newstyleservice.common_ktx.SharedPrefDelegated

class MyPreferences(context: Context) : SharedPrefDelegated(context) {
    var touchCount: Long by pref(default = 0)
}