package com.example.composetweetsapp.repo

import com.example.composetweetsapp.model.TweetListItem

interface TweetsRepo {
    suspend fun getTopics(): Result<List<String>?>

    suspend fun getTweetsByTopic(topic: String): Result<List<TweetListItem>?>
}