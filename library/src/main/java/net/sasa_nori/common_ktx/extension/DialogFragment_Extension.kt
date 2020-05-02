package net.sasa_nori.common_ktx.extension

import androidx.fragment.app.DialogFragment

fun DialogFragment.hasPermission(permissions: Array<String>): Boolean =
    requireContext().hasRuntimePermissions(permissions)

fun DialogFragment.delete() {
    // フラグメントが表示されていなければ処理なし
    val prev = parentFragmentManager.findFragmentByTag(tag) as DialogFragment? ?: return

    // ダイアログがなければ処理なし
    val dialog = prev.dialog ?: return

    // ダイアログが表示されていなければ処理なし
    if (!dialog.isShowing) {
        return
    }
    // ダイアログ消去通知と消去
    prev.dismiss()
}
