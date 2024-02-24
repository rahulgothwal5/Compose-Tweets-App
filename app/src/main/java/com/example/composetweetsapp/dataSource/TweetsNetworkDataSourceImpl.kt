package com.example.composetweetsapp.dataSource

import com.example.composetweetsapp.model.TweetListItem
import com.example.composetweetsapp.service.TweetsApi
import retrofit2.Response
import javax.inject.Inject

class TweetsNetworkDataSourceImpl @Inject constructor(val tweetsApi: TweetsApi) : TweetsNetworkDataSource {
    override suspend fun fetchTopicsFromRemote(): Response<List<String>> {
        return tweetsApi.getTopics()
    }

    override suspend fun fetchTweetsForTopicFromRemote(topic: String): Response<List<TweetListItem>> {
        return tweetsApi.getTweets(topic = ("tweets[?(@.topic==\"$topic\")]"))
    }
}