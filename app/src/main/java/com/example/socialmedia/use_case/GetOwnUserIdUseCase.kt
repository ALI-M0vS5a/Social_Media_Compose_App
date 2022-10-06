package com.example.socialmedia.use_case

import android.content.SharedPreferences
import com.example.socialmedia.util.Constants


class GetOwnUserIdUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: ""
    }
}