package net.newstyleservice.common_ktx.extension

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Fragment.hasPermission(permissions: List<String>): Boolean =
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