package com.emreakbay.pocketrealm.core.game

sealed interface GameEvent {

    data class FarmUpgraded(
        val foodGained: Int,
        val goldSpent: Int
    ) : GameEvent

    data class FoodPurchased(
        val foodGained: Int,
        val goldSpent: Int
    ) : GameEvent

    data class DayEnded(
        val newDay: Int
    ) : GameEvent

    data class NotEnoughGold(
        val required: Int
    ) : GameEvent
}