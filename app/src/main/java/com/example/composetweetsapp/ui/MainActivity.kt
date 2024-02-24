package com.example.composetweetsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetweetsapp.ui.screen.DetailScreen
import com.example.composetweetsapp.ui.screen.TopicScreen
import com.example.composetweetsapp.ui.theme.ComposeTweetsAppTheme
import com.example.composetweetsapp.viewModel.TweetHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTweetsAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        AppBar {

                        }
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun App() {


    val navController = rememberNavController()

    NavHost(navController, startDestination = "category") {
        composable(
            route = "category",
        ) {
            TopicScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(
            route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailScreen()
        }
    }


}


@Composable
fun testCode() {
    val luckyNumber = remember {
        generateLuckyNumber()
    }
    var time by remember { mutableStateOf(0) }

    Column {
        Text(text = "My lucky no. is : $luckyNumber")
        Text(text = "The time is : $time")
    }

    LaunchedEffect(key1 = Unit, block = {
        launch {
            while (true) {
                delay(1000)
                time++
            }


        }
    })
}

private fun generateLuckyNumber(): Int {
    return Random.nextInt()
}
