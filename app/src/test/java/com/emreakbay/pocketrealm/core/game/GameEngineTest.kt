package com.emreakbay.pocketrealm.core.game

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameEngineTest {

    private val engine = GameEngine()

    @Test
    fun upgradeFarm_spendsGoldAndAddsFood() {
        val initialState = GameState(
            day = 1,
            food = 100,
            gold = 100,
            population = 10
        )

        val result = engine.execute(
            state = initialState,
            action = GameAction.UpgradeFarm
        )

        assertEquals(70, result.state.gold)
        assertEquals(130, result.state.food)

        assertTrue(
            result.event is GameEvent.FarmUpgraded
        )
    }

    @Test
    fun upgradeFarm_doesNothingWhenGoldIsInsufficient() {
        val initialState = GameState(
            food = 100,
            gold = 10,
            population = 10
        )

        val result = engine.execute(
            state = initialState,
            action = GameAction.UpgradeFarm
        )

        assertEquals(10, result.state.gold)
        assertEquals(100, result.state.food)

        assertTrue(
            result.event is GameEvent.NotEnoughGold
        )
    }

    @Test
    fun endDay_increasesDayAndConsumesFood() {
        val initialState = GameState(
            day = 1,
            food = 100,
            gold = 100,
            population = 10
        )

        val result = engine.execute(
            state = initialState,
            action = GameAction.EndDay
        )

        assertEquals(2, result.state.day)
        assertEquals(90, result.state.food)
    }
}