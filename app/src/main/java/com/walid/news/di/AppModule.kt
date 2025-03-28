package com.walid.news.di


import dagger.Provides
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.walid.news.data.AppConstants
import com.walid.news.data.api.ApiService
import com.walid.news.data.datasource.NewsDataSource
import com.walid.news.data.datasource.NewsDataSourceImp
import com.walid.news.ui.repository.NewsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton

    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BASIC
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS )
        }


val moshi=Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()



        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton

    fun providesNewsDataSource(apiService: ApiService): NewsDataSource{
return NewsDataSourceImp(apiService)
    }

    @Provides
    @Singleton

    fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepository{
        return NewsRepository(newsDataSource)
    }

}