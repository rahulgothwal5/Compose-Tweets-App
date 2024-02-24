package com.example.composetweetsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetweetsapp.viewModel.TweetHomeViewModel
import com.example.composetweetsapp.viewModel.TweetListViewModel

@Composable
fun DetailScreen() {
    val detailViewModel: TweetListViewModel = hiltViewModel()
    val tweets = detailViewModel.tweets.collectAsState()



    if (tweets.value !is TweetListViewModel.TweetListUiState.Success) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineSmall)
        }
    } else {
        tweets.value?.let {
            LazyColumn(content = {
                val list =
                    (tweets.value as TweetListViewModel.TweetListUiState.Success).tweets
                items(list.size) {
                    TweetListItem(tweet = list[it].tweet)
                }
            })
        }
    }




}

@Composable
fun TweetListItem(tweet: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}