package com.walid.news.ui.repository



import com.walid.news.data.datasource.NewsDataSource
import com.walid.news.data.entity.NewsResponse

import com.walid.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

   fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>>{
        return flow {
            emit(ResourceState.Loading())

            val response =newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body()!=null){

                emit(ResourceState.Success(response.body()!!))

            }else {
                emit(ResourceState.Error("Error fetching data"))
            }
        }.catch {e->
            emit(ResourceState.Error(e.localizedMessage ?:"Some error in flow"))
        }
    }


}