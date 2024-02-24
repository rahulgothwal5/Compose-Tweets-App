package com.example.composetweetsapp.useCase

import com.example.composetweetsapp.common.Result
import com.example.composetweetsapp.repo.TweetsRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTweetsForTopicUseCase @Inject constructor(
    private val repository: TweetsRepo
) {

    operator fun invoke(topic: String) = flow {
        try {
            val result = repository.getTweetsByTopic(topic)
            if (result.isSuccess) {
                emit(Result.Success(result.getOrNull()))
            } else {
                emit(Result.Error(Exception("Failed to fetch tweets for topic '$topic': ${result.exceptionOrNull()?.message}")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}