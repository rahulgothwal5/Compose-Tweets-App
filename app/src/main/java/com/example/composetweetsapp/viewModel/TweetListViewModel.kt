package com.example.composetweetsapp.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetweetsapp.common.Result
import com.example.composetweetsapp.model.TweetListItem
import com.example.composetweetsapp.useCase.GetTweetsForTopicUseCase
import com.example.composetweetsapp.viewModel.TweetListViewModel.TweetListUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetListViewModel @Inject constructor(
    private val getTweetsForTopicUseCase: GetTweetsForTopicUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _tweets: MutableStateFlow<TweetListUiState?> =
        MutableStateFlow(null)
    val tweets = _tweets


    private fun fetchTopics() {
        viewModelScope.launch {
            val topic = savedStateHandle.get<String>("topic") ?: "System Design"
            getTweetsForTopicUseCase.invoke(topic).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _tweets.value = Success(result.data!!)
                    }

                    is Result.Error -> {
                        _tweets.value = TweetListUiState.Error(result.exception)
                    }
                }
            }
        }
    }

    init {
        fetchTopics()
    }

    sealed class TweetListUiState {
        data class Success(val tweets: List<TweetListItem>) : TweetListUiState()
        data class Error(val exception: Exception) : TweetListUiState()
    }


}