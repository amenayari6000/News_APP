package com.walid.news.data.api

import com.walid.news.data.entity.NewsResponse
import retrofit2.Response


import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

@GET("v2/top-headlines")

  suspend fun getNewsHeadline(
    @Query("country")country: String,
    @Query("apiKey")apiKey: String="your api key from "newsapi.org"
) :Response<NewsResponse>



}
