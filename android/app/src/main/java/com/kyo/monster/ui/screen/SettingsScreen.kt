package com.kyo.monster.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kyo.monster.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(stringResource(R.string.settings_title))
    }
}
