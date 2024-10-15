package com.kyo.monster.ui.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kyo.monster.R
import com.kyo.monster.data.model.Monster
import com.kyo.monster.data.model.MonsterRating
import com.kyo.monster.data.model.MonsterType
import com.kyo.monster.util.findActivity

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler {
        context.findActivity()?.finish()
    }
    LaunchedEffect(true) {
        homeViewModel.getMonsters()
    }
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    HomeScreenContent(homeUiState, modifier)
}

@Composable
fun HomeScreenContent(homeUiState: HomeUiState, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.home_title),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        HomeMonsterListSection(
            homeUiState.monsters
        )
    }
}

@Composable
fun HomeMonsterListSection(monsters: List<Monster>) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)) {
        items(monsters, key = { it.id }) { monster ->
            MonsterCard(monster)
        }
    }
}

@Composable
fun MonsterCard(monster: Monster) {
    Box(
        modifier = Modifier.height(400.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(20.dp)) {
            Image(
                painterResource(monster.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = monster.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stringResource(R.string.hp_value, monster.hp),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = monster.rate.toString(),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    val monsters = listOf(
        Monster(
            id = "4",
            name = "Shadow",
            hp = 100,
            type = listOf(MonsterType.DRAGON, MonsterType.DARK),
            rate = MonsterRating.EXCELLENT,
            image = R.drawable.img_shadow
        ), Monster(
            id = "5",
            name = "Solara",
            hp = 100,
            type = listOf(MonsterType.DRAGON, MonsterType.LIGHT),
            rate = MonsterRating.EXCELLENT,
            image = R.drawable.img_solara
        )
    )
    val homeUiState = HomeUiState(monsters = monsters)
    HomeScreenContent(homeUiState = homeUiState, modifier = Modifier.fillMaxSize())
}
