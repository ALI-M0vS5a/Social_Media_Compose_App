package com.example.socialmedia.feature_post.presentation.util

import android.content.Context
import android.view.inputmethod.InputMethodManager


fun Context.showKeyBoard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(null,InputMethodManager.SHOW_IMPLICIT)
}