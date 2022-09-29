package com.example.socialmedia.feature_auth.data.repository

import android.content.SharedPreferences
import com.example.socialmedia.R
import com.example.socialmedia.feature_auth.data.dto.request.CreateAccountRequest
import com.example.socialmedia.feature_auth.data.dto.request.LoginRequest
import com.example.socialmedia.feature_auth.data.remote.AuthApi
import com.example.socialmedia.feature_auth.domain.repository.AuthRepository
import com.example.socialmedia.util.Constants
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText
import retrofit2.HttpException
import java.io.IOException


class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        val request = CreateAccountRequest(email, username, password)
        return try {
            val response = api.register(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(
                        UiText.DynamicString(msg)
                    )
                } ?: Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.unknown_error
                    )
                )
            }
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.please_check_your_connection
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.Oops_something_went_wrong
                )
            )
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): SimpleResource {
        val request = LoginRequest(email, password)
        return try {
            val response = api.login(request)
            if (response.successful) {
                response.data?.token?.let { token ->
                    sharedPreferences
                        .edit()
                        .putString(Constants.KEY_JWT_TOKEN, token)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.unknown_error
                    )
                )
            }
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.please_check_your_connection
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.Oops_something_went_wrong
                )
            )
        }
    }

    override suspend fun authenticate(): SimpleResource {
        return try {
            api.authenticate()
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.please_check_your_connection
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(
                    resId = R.string.Oops_something_went_wrong
                )
            )
        }
    }
}