package com.ulys.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.ulys.ActionProcessor
import com.ulys.systems.*

class GameScreen : ScreenAdapter() {

    private val engine = PooledEngine()

    override fun show() {
        val systems = arrayOf(
            Cloud(),
            BosMage(),
            CitySystem(),
            CleanUpSystem(),
            ArmySpawnerSystem(),
            LavaEmitterSystem(),
            MovementSystem(),
            Collision(),
            AnimationSystem(),
            RenderingSystem(),
            HealthRenderSystem(),
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