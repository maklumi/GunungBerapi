package com.ulys.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ScreenAdapter
import com.ulys.EntityFactory
import com.ulys.systems.DebugSystem
import com.ulys.systems.RenderingSystem

class GameScreen : ScreenAdapter() {

    private val engine = PooledEngine()
    private val factory = EntityFactory(engine)

    override fun show() {
        val systems = arrayOf(
            RenderingSystem(),
            DebugSystem(),
        )
        systems.forEach { engine.addSystem(it) }

        factory.addEntiti()
    }

    override fun render(delta: Float) {
        engine.update(delta)
    }
}