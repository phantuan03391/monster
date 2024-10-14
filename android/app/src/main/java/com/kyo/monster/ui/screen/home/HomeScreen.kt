package com.kyo.monster.ui.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
            homeUiState.monsters,
            modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Composable
fun HomeMonsterListSection(monsters: List<Monster>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(monsters, key = { it.id }) { monster ->
            Text(text = monster.name, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    val monsters = listOf(
        Monster(
            id = "3",
            name = "Blastoise",
            hp = 85,
            type = listOf(MonsterType.WATER),
            rate = MonsterRating.GOOD
        ), Monster(
            id = "4",
            name = "Venusaur",
            hp = 80,
            type = listOf(MonsterType.EARTH),
            rate = MonsterRating.GOOD
        )
    )
    val homeUiState = HomeUiState(monsters = monsters)
    HomeScreenContent(homeUiState = homeUiState, modifier = Modifier.fillMaxSize())
}
