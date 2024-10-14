package com.kyo.monster.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.kyo.monster.R
import com.kyo.monster.util.findActivity

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    BackHandler {
        context.findActivity()?.finish()
    }
    Column(modifier = modifier) {
        Text(stringResource(R.string.home_title))
    }
}
