package com.walid.news.data.datasource

import com.walid.news.data.api.ApiService
import com.walid.news.data.entity.NewsResponse

import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : NewsDataSource{


    override  suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
      return apiService.getNewsHeadline(country)
    }
}