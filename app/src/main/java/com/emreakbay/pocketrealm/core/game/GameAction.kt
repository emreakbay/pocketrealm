package com.emreakbay.pocketrealm.core.game

sealed interface GameAction {

    data object UpgradeFarm : GameAction

    data object BuyFood : GameAction

    data object EndDay : GameAction
}