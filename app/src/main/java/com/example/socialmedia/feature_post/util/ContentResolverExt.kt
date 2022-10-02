package com.example.socialmedia.feature_post.util

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns

fun ContentResolver.getFileName(uri: Uri): String {
    val returnCursor = query(uri,null,null,null,null,null) ?: return ""
    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToNext()
    val fileName = returnCursor.getString(nameIndex)
    returnCursor.close()
    return fileName

}