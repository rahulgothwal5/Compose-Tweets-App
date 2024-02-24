package com.example.composetweetsapp.useCase

import com.example.composetweetsapp.repo.TweetsRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.composetweetsapp.common.Result

class GetTopicsUseCase @Inject constructor(
    private val repository: TweetsRepo
) {


    operator fun invoke() = flow {
        try {
            val result = repository.getTopics()
            if (result.isSuccess) {
                emit(Result.Success(result.getOrNull()))
            } else {
                emit(Result.Error(Exception("Failed to fetch tweets for topic '': ${result.exceptionOrNull()?.message}")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}