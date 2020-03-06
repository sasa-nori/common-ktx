package net.newstyleservice.common_ktx.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Activity.hasPermission(permissions: List<String>): Boolean =
    this.hasRuntimePermissions(permissions)

@SuppressLint("ResourceType")
fun AppCompatActivity.addFragment(
    @LayoutRes container: Int, fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commit()
}

@SuppressLint("ResourceType")
fun AppCompatActivity.addNowFragment(
    @LayoutRes container: Int, fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commitNow()
}

@SuppressLint("ResourceType")
fun AppCompatActivity.replaceFragment(
    @LayoutRes container: Int, fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commit()
}

@SuppressLint("ResourceType")
fun AppCompatActivity.replaceNowFragment(
    @LayoutRes container: Int, fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commitNow()
}