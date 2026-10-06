package com.emreakbay.pocketrealm.core.game

data class GameResult(
    val state: GameState,
    val event: GameEvent
)

class GameEngine {

    fun execute(
        state: GameState,
        action: GameAction
    ): GameResult {
        return when (action) {
            GameAction.UpgradeFarm -> upgradeFarm(state)
            GameAction.BuyFood -> buyFood(state)
            GameAction.EndDay -> endDay(state)
        }
    }

    private fun upgradeFarm(
        state: GameState
    ): GameResult {

        val goldCost = 30
        val foodGain = 30

        if (state.gold < goldCost) {
            return GameResult(
                state = state,
                event = GameEvent.NotEnoughGold(goldCost)
            )
        }

        val newState = state.copy(
            gold = state.gold - goldCost,
            food = state.food + foodGain
        )

        return GameResult(
            state = newState,
            event = GameEvent.FarmUpgraded(
                foodGained = foodGain,
                goldSpent = goldCost
            )
        )
    }

    private fun buyFood(
        state: GameState
    ): GameResult {

        val goldCost = 20
        val foodGain = 20

        if (state.gold < goldCost) {
            return GameResult(
                state = state,
                event = GameEvent.NotEnoughGold(goldCost)
            )
        }

        val newState = state.copy(
            gold = state.gold - goldCost,
            food = state.food + foodGain
        )

        return GameResult(
            state = newState,
            event = GameEvent.FoodPurchased(
                foodGained = foodGain,
                goldSpent = goldCost
            )
        )
    }

    private fun endDay(
        state: GameState
    ): GameResult {

        val foodConsumed = state.population

        val newState = state.copy(
            day = state.day + 1,
            food = (state.food - foodConsumed).coerceAtLeast(0)
        )

        return GameResult(
            state = newState,
            event = GameEvent.DayEnded(
                newDay = newState.day
            )
        )
    }
}