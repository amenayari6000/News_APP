package com.walid.news.data.datasource


import com.walid.news.data.entity.NewsResponse
import retrofit2.Response


interface NewsDataSource {


   suspend fun getNewsHeadline(country: String): Response<NewsResponse>

}