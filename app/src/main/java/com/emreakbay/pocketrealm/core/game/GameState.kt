package com.emreakbay.pocketrealm.core.game

data class GameState(
    val day: Int = 1,
    val food: Int = 100,
    val gold: Int = 100,
    val population: Int = 10
)