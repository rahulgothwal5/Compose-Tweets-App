package com.example.composetweetsapp.repo

import com.example.composetweetsapp.dataSource.TweetsNetworkDataSource
import com.example.composetweetsapp.model.TweetListItem
import kotlinx.coroutines.CloseableCoroutineDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetsRepoImpl @Inject constructor(
    private val dataSource: TweetsNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TweetsRepo {
    override suspend fun getTopics(): Result<List<String>?> {
        return withContext(ioDispatcher) {
            try {
                val response = dataSource.fetchTopicsFromRemote()
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to fetch topics: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getTweetsByTopic(topic: String): Result<List<TweetListItem>?> {
        return withContext(ioDispatcher) {
            try {
                val response = dataSource.fetchTweetsForTopicFromRemote(topic)
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to fetch tweets for topic '$topic': ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


}