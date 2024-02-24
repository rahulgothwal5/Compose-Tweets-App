package com.example.composetweetsapp.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetweetsapp.common.standardQuadFromTo
import com.example.composetweetsapp.model.ColorPallet
import com.example.composetweetsapp.ui.theme.Beige1
import com.example.composetweetsapp.ui.theme.Beige2
import com.example.composetweetsapp.ui.theme.Beige3
import com.example.composetweetsapp.ui.theme.BlueViolet1
import com.example.composetweetsapp.ui.theme.BlueViolet2
import com.example.composetweetsapp.ui.theme.BlueViolet3
import com.example.composetweetsapp.ui.theme.LightGreen1
import com.example.composetweetsapp.ui.theme.LightGreen2
import com.example.composetweetsapp.ui.theme.LightGreen3
import com.example.composetweetsapp.ui.theme.OrangeYellow1
import com.example.composetweetsapp.ui.theme.OrangeYellow2
import com.example.composetweetsapp.ui.theme.OrangeYellow3
import com.example.composetweetsapp.viewModel.TweetHomeViewModel
import kotlin.random.Random

@Composable
fun TopicScreen(onClick: (String) -> Unit) {
    val colorList by remember {
        mutableStateOf(
            listOf(
                ColorPallet(
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                ColorPallet(
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                ColorPallet(
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                ColorPallet(
                    Beige1,
                    Beige2,
                    Beige3
                ), ColorPallet(
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                )
            )
        )
    }
    val tweetsHomeViewModel: TweetHomeViewModel = hiltViewModel()
    val topics = tweetsHomeViewModel.topics.collectAsState()

    if (topics.value !is TweetHomeViewModel.TweetHomeUiState.Success) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineSmall)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            val list =
                (topics?.value as TweetHomeViewModel.TweetHomeUiState.Success).topics.distinct()
            items(list.size) {
                val item = list[it]
                val colorPallet = colorList[it % colorList.size]
                TopicItem(category = item,colorPallet, onClick)
            }
        }
    }
}

@Composable
fun TopicItem(category: String, colorPallet: ColorPallet, onClick: (category: String) -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.
            clickable { onClick(category) }
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(colorPallet.darkColor)
    ) {

        CardBackground(colorPallet)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Text(
                text = category,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 20.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
private fun BoxWithConstraintsScope.CardBackground(colorPallet: ColorPallet) {
    val width = constraints.maxWidth
    val height = constraints.maxHeight

    // Medium colored path
    val mediumColoredPoint1 = Offset(0f, height * 0.3f)
    val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
    val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
    val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

    val mediumColoredPath = Path().apply {
        moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
        standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
        standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
        standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
        standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }

    // Light colored path
    val lightPoint1 = Offset(0f, height * 0.35f)
    val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
    val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
    val lightPoint4 = Offset(width * 0.65f, height.toFloat())
    val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

    val lightColoredPath = Path().apply {
        moveTo(lightPoint1.x, lightPoint1.y)
        standardQuadFromTo(lightPoint1, lightPoint2)
        standardQuadFromTo(lightPoint2, lightPoint3)
        standardQuadFromTo(lightPoint3, lightPoint4)
        standardQuadFromTo(lightPoint4, lightPoint5)
        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        drawPath(
            path = mediumColoredPath,

            color = colorPallet.mediumColor
        )
        drawPath(
            path = lightColoredPath,
            color = colorPallet.lightColor
        )
    }
}