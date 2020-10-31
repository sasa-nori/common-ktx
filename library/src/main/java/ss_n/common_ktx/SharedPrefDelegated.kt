package ss_n.common_ktx

import android.content.Context
import android.content.SharedPreferences
import ss_n.common_ktx.extension.getPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class SharedPrefDelegated(
    private val context: Context,
    private val fileName: String? = null
) {

    private val appPref: SharedPreferences
        get() = fileName?.let { context.getPreferences(name = it) } ?: context.getPreferences()

    protected fun <T : Any> pref(default: T) = object : ReadWriteProperty<SharedPrefDelegated, T> {

        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: SharedPrefDelegated, property: KProperty<*>): T {
            val key = property.name
            return (appPref.all[key] as? T) ?: run {
                put(key, default)
                default
            }
        }

        override fun setValue(thisRef: SharedPrefDelegated, property: KProperty<*>, value: T) {
            val key = property.name
            put(key, value)
        }
    }

    protected fun <T : Any?> nullablePref() = object : ReadWriteProperty<SharedPrefDelegated, T?> {

        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: SharedPrefDelegated, property: KProperty<*>): T? {
            val key = property.name
            return appPref.all[key] as? T?
        }

        override fun setValue(thisRef: SharedPrefDelegated, property: KProperty<*>, value: T?) {
            val key = property.name
            put(key, value)
        }
    }

    protected fun <T : Any?> put(key: String, value: T?) {
        val editor = appPref.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Set<*> -> editor.putStringSet(key, value.map { it as String }.toSet())
            null -> editor.remove(key)
            else -> throw IllegalArgumentException("UnPreparation Class ")
        }
        editor.apply()
    }
}