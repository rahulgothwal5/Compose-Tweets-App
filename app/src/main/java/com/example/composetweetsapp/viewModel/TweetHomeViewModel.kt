package com.example.composetweetsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetweetsapp.useCase.GetTopicsUseCase
import com.example.composetweetsapp.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetHomeViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase,
) : ViewModel() {

    private val _topics: MutableStateFlow<TweetHomeUiState?> = MutableStateFlow(null)
    val topics = _topics


    private fun fetchTopics() {
        viewModelScope.launch {
            getTopicsUseCase.invoke().collect { result ->
                when (result) {

                    is Result.Success -> {
                        _topics.value = TweetHomeUiState.Success(result.data!!)
                    }

                    is Result.Error -> {
                        _topics.value = TweetHomeUiState.Error(result.exception)
                    }
                }
            }
        }
    }

    init {
        fetchTopics()
    }

    sealed class TweetHomeUiState {
        data class Success(val topics: List<String>) : TweetHomeUiState()
        data class Error(val exception: Exception) : TweetHomeUiState()
    }
}