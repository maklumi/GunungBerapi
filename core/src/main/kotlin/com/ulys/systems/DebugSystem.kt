package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ulys.Bounds
import com.ulys.DebugTag
import com.ulys.boundsMapper
import ktx.ashley.allOf

class DebugSystem : IteratingSystem(allOf(DebugTag::class, Bounds::class).get()) {

    private val renderer = ShapeRenderer()
    private lateinit var rs: RenderingSystem

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        rs = engine.getSystem(RenderingSystem::class.java)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        Gdx.gl20.glLineWidth(1f)
        renderer.projectionMatrix = rs.camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.YELLOW

        val b = boundsMapper[entity]
        renderer.rect(b.bounds.x, b.bounds.y, b.bounds.width, b.bounds.height)
        renderer.circle(
            b.bounds.x + b.bounds.width / 2,
            b.bounds.y + b.bounds.height / 2,
            .4f
        )

        renderer.end()
    }


}