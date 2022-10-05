package com.example.socialmedia.feature_profile.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.data.remote.ProfileApi
import com.example.socialmedia.feature_profile.domain.model.Profile
import com.example.socialmedia.feature_profile.domain.model.Skill
import com.example.socialmedia.feature_profile.domain.model.UpdateProfileData
import com.example.socialmedia.feature_profile.domain.repository.ProfileRepository
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException


class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val gson: Gson
) : ProfileRepository {
    override suspend fun getProfile(userId: String): Resource<Profile> {
        return try {
            val response = api.getProfile(userId)
            if (response.successful) {
                Resource.Success(response.data?.toProfile())
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.unknown_error))
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

    override suspend fun getSkills(): Resource<List<Skill>> {
        return try {
            val response = api.getSkills()
            Resource.Success(
                data = response.map { it.toSkill() }
            )
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

    override suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource {
        val bannerFile = bannerImageUri?.toFile()
        val profilePictureFile = profilePictureUri?.toFile()

        return try {
            val response = api.updateProfile(
                bannerImage = bannerFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "banner_image",
                            bannerFile.name,
                            bannerFile.asRequestBody()
                        )
                },
                profilePicture = profilePictureFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "profile_picture",
                            profilePictureFile.name,
                            profilePictureFile.asRequestBody()
                        )
                },
                updateProfileData = MultipartBody.Part
                    .createFormData(
                        "update_profile_data",
                        gson.toJson(updateProfileData)
                    )
            )
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.unknown_error))
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
}