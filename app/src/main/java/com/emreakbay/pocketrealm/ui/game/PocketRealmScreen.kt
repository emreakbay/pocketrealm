package com.emreakbay.pocketrealm.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emreakbay.pocketrealm.core.game.GameAction
import com.emreakbay.pocketrealm.core.game.GameEngine
import com.emreakbay.pocketrealm.core.game.GameState

@androidx.compose.runtime.Composable
fun PocketRealmScreen() {
    val engine = remember { GameEngine() }
    var state by remember { mutableStateOf(GameState()) }

    fun execute(action: GameAction) {
        state = engine.execute(
            state = state,
            action = action
        ).state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "PocketRealm",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Gün ${state.day}")
                Text("🍖 Yiyecek: ${state.food}")
                Text("💰 Altın: ${state.gold}")
                Text("👥 Nüfus: ${state.population}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                execute(GameAction.UpgradeFarm)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Çiftliği Geliştir")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                execute(GameAction.BuyFood)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Yiyecek Satın Al")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                execute(GameAction.EndDay)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Günü Bitir")
        }
    }
}