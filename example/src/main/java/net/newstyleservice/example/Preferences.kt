package net.newstyleservice.example

import android.content.Context
import net.newstyleservice.common_ktx.SharedPrefDelegated

class Preferences(context: Context) : SharedPrefDelegated(context) {
    var isFirstLaunch: Boolean by pref(default = true)
}