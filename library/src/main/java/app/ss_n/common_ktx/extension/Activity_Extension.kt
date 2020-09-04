package app.ss_n.common_ktx.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Activity.hasPermission(permissions: Array<String>): Boolean =
    this.hasRuntimePermissions(permissions)

fun AppCompatActivity.showDialog(
    dialog: DialogFragment,
    arg: Bundle? = null,
    tag: String = dialog.javaClass.canonicalName ?: "showDialog"
) {
    arg?.let {
        dialog.arguments = arg
    }
    dialog.show(supportFragmentManager, tag)
}

@SuppressLint("ResourceType")
fun AppCompatActivity.addFragment(
    @LayoutRes container: Int,
    fragment: Fragment,
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
    @LayoutRes container: Int,
    fragment: Fragment,
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
    @LayoutRes container: Int,
    fragment: Fragment,
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
    @LayoutRes container: Int,
    fragment: Fragment,
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

/**
 * Do not display keyboard in initial display of Activity
 */
fun Activity.initHiddenKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

/**
 * Show Gallery of Devise
 *
 * @param requestCode requestCode of [Activity.startActivityForResult]
 */
fun Activity.showGallery(requestCode: Int = 1) {
    val photoPickerIntent = Intent(Intent.ACTION_PICK)
    photoPickerIntent.type = "image/*"
    startActivityForResult(photoPickerIntent, requestCode)
}
