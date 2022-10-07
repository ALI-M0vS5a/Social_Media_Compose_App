package com.example.socialmedia.feature_activity.domain.di

import com.example.socialmedia.feature_activity.data.remote.dto.ActivityApi
import com.example.socialmedia.feature_activity.data.repository.ActivityRepositoryImpl
import com.example.socialmedia.feature_activity.domain.repository.ActivityRepository
import com.example.socialmedia.feature_activity.domain.use_case.GetActivitiesUseCase
import com.example.socialmedia.util.BaseUrl.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {



    @Provides
    @Singleton
    fun provideActivityApi(client: OkHttpClient): ActivityApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivityApi::class.java)
    }
    @Provides
    @Singleton
    fun provideActivitiesRepository(api: ActivityApi): ActivityRepository {
        return ActivityRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideGetActivitiesUseCase(repository: ActivityRepository): GetActivitiesUseCase {
        return GetActivitiesUseCase(repository)
    }
}