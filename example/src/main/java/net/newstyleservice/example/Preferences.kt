package net.newstyleservice.example

import android.content.Context
import ss_n.common_ktx.SharedPrefDelegated

class Preferences(context: Context) : SharedPrefDelegated(context) {
    var tapCount: Int by pref(default = 0)
}
