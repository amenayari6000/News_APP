package com.walid.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walid.news.data.AppConstants
import com.walid.news.data.entity.NewsResponse

import com.walid.news.ui.repository.NewsRepository
import com.walid.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
//import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news: MutableStateFlow<ResourceState<NewsResponse>> =
           MutableStateFlow(ResourceState.Loading()) // her in viewmodel can change it

    val news : StateFlow<ResourceState<NewsResponse>> = _news// her not change in ui

    init {
        getNews(AppConstants.COUNTRY)
    }



   private fun getNews(country : String){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadline(country)
                .collectLatest {newsResponse ->

                    _news.value=newsResponse
                }
        }

    }




}