package com.ulys.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.ulys.ActionProcessor
import com.ulys.EntityFactory
import com.ulys.systems.DebugSystem
import com.ulys.systems.LavaEmitterSystem
import com.ulys.systems.LavaFallSystem
import com.ulys.systems.RenderingSystem

class GameScreen : ScreenAdapter() {

    private val engine = PooledEngine()
    private val factory = EntityFactory(engine)

    override fun show() {
        val systems = arrayOf(
            LavaEmitterSystem(),
            LavaFallSystem(),
            RenderingSystem(),
            DebugSystem(),
        )
        systems.forEach { engine.addSystem(it) }

        Gdx.input.inputProcessor = ActionProcessor(Input.Keys.F, Input.Keys.J, Input.Keys.SPACE)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)
    }
}