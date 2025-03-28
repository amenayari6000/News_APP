package com.walid.news.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.walid.news.ui.components.EmptyStateComponent

import com.walid.news.ui.components.Loader
//import com.walid.news.ui.components.NewsList
import com.walid.news.ui.components.NewsRowComponent
import com.walid.news.ui.viewmodel.NewsViewModel
import com.walid.utilities.ResourceState


const val TAG ="HomeScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsRes = newsViewModel.news.collectAsState()

    when (val currentNews = newsRes.value) {
        is ResourceState.Loading -> {
            Loader()
        }

        is ResourceState.Success -> {
            val response = currentNews.data
            val articles = response.articles

            Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")

            if (articles.isEmpty()) {
                EmptyStateComponent()
            } else {
                val pagerState = rememberPagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0F
                ) { articles.size } // Set the page count dynamically

                VerticalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    pageSize = PageSize.Fill,
                    pageSpacing = 8.dp
                ) { page: Int ->
                    NewsRowComponent(page, articles[page])
                }
            }
        }

        is ResourceState.Error -> {
            EmptyStateComponent()
            Log.d(TAG, "Error fetching news: ${currentNews.error}")
        }
    }
}

@Preview
@Composable

fun HomeScreenPreview(){
    HomeScreen()
}

