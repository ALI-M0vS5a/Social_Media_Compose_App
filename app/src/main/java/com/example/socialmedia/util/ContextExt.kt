package com.example.socialmedia.util

import android.content.Context
import android.content.Intent
import com.example.socialmedia.R

fun Context.sendSharedPostIntent(postId: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            getString(
                R.string.share_intent_text,
                "https://montymobile.com/$postId"
            )
        )
        type = "text/plain"
    }
    if(intent.resolveActivity(packageManager) != null) {
        startActivity(Intent.createChooser(intent, "Select an app"))
    }
}