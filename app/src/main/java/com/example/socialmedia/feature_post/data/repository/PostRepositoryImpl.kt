package com.example.socialmedia.feature_post.data.repository


import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.socialmedia.R
import com.example.socialmedia.domain.models.Post
import com.example.socialmedia.feature_post.data.data_source.remote.PostApi
import com.example.socialmedia.feature_post.data.data_source.remote.request.CreatePostRequest
import com.example.socialmedia.feature_post.data.paging.PostSource
import com.example.socialmedia.feature_post.domian.repository.PostRepository
import com.example.socialmedia.feature_post.util.getFileName
import com.example.socialmedia.util.Constants
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.SimpleResource
import com.example.socialmedia.util.UiText
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class PostRepositoryImpl(
    private val api: PostApi,
    private val gson: Gson
) : PostRepository {
    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE_POSTS)) {
            PostSource(api)
        }.flow

    override suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource {
        val request = CreatePostRequest(description)
        val file = withContext(Dispatchers.IO) {
//            context.contentResolver.openFileDescriptor(imageUri, "r")?.let { fd ->
//                val inputStream = FileInputStream(fd.fileDescriptor)
//                val file = File(
//                    context.cacheDir,
//                    context.contentResolver.getFileName(imageUri)
//                )
//                val outputStream = FileOutputStream(file)
//                inputStream.copyTo(outputStream)
//                file
//            }
        imageUri.toFile()
        } ?: return Resource.Error(
            message = UiText.StringResource(R.string.error_file_not_found)
        )
        return try {
            val response = api.createPost(
                postData = MultipartBody.Part
                    .createFormData(
                        name = "post_data",
                        gson.toJson(request)
                    ),
                postImage = MultipartBody.Part
                    .createFormData(
                        name = "post_image",
                        filename = file.name,
                        body = file.asRequestBody()
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