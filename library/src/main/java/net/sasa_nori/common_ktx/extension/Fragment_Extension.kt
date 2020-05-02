package net.sasa_nori.common_ktx.extension

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Fragment.hasPermission(permissions: Array<String>): Boolean =
    requireContext().hasRuntimePermissions(permissions)

fun Fragment.showDialog(
    dialog: DialogFragment,
    arg: Bundle? = null,
    tag: String = dialog.javaClass.canonicalName.toString()
) {
    arg?.let {
        dialog.arguments = arg
    }
    dialog.show(childFragmentManager, tag)
}

@SuppressLint("ResourceType")
fun Fragment.addFragment(
    @LayoutRes container: Int,
    fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = childFragmentManager.beginTransaction()
    transaction.add(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commit()
}

@SuppressLint("ResourceType")
fun Fragment.addNowFragment(
    @LayoutRes container: Int,
    fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = childFragmentManager.beginTransaction()
    transaction.add(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commitNow()
}

@SuppressLint("ResourceType")
fun Fragment.replaceFragment(
    @LayoutRes container: Int,
    fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commit()
}

@SuppressLint("ResourceType")
fun Fragment.replaceNowFragment(
    @LayoutRes container: Int,
    fragment: Fragment,
    arg: Bundle? = null,
    isAddStack: Boolean = false,
    stackTag: String? = null
) {
    arg?.let {
        fragment.arguments = it
    }
    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(container, fragment)
    if (isAddStack) {
        transaction.addToBackStack(stackTag)
    }
    transaction.commitNow()
}
