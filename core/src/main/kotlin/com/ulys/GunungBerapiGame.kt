package com.ulys

import com.badlogic.gdx.Game
import com.ulys.screen.GameScreen

class GunungBerapiGame : Game() {
    override fun create() {
        setScreen(GameScreen())
    }
}