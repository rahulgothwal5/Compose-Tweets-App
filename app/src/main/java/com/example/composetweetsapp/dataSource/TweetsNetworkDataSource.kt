package com.example.composetweetsapp.dataSource

import com.example.composetweetsapp.model.TweetListItem
import retrofit2.Response

interface TweetsNetworkDataSource {

    suspend fun fetchTopicsFromRemote(): Response<List<String>>

    suspend fun fetchTweetsForTopicFromRemote(topic: String): Response<List<TweetListItem>>
}