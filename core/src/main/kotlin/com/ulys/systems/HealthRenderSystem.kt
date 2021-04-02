package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ulys.Bounds
import com.ulys.Health
import com.ulys.boundsMapper
import com.ulys.healthMapper
import ktx.ashley.allOf

class HealthRenderSystem : IteratingSystem(allOf(Health::class, Bounds::class).get()) {

    private val filled = ShapeRenderer()
    private val lined = ShapeRenderer()
    private lateinit var cam: OrthographicCamera

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        cam = engine.getSystem(RenderingSystem::class.java).camera
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        Gdx.gl20.glLineWidth(0.5f)
        filled.projectionMatrix = cam.combined
        filled.begin(ShapeRenderer.ShapeType.Filled)

        val bc = boundsMapper[entity]
        val hc = healthMapper[entity]
        val healthWidth = bc.bounds.width * (hc.health / hc.maxHealth)
        filled.color = when {
            healthWidth < 0.8f -> Color.YELLOW
            healthWidth < 0.6f -> Color.RED
            else -> Color.GREEN
        }
        filled.rect(
            bc.bounds.x, bc.bounds.y + bc.bounds.height + 0.25f,
            healthWidth, 0.25f
        )
        filled.end()

        //Outline
        lined.projectionMatrix = cam.combined
        lined.begin(ShapeRenderer.ShapeType.Line)
        lined.color = Color.WHITE
        lined.rect(
            bc.bounds.x, bc.bounds.y + bc.bounds.height + 0.25f,
            bc.bounds.width, 0.25f
        )
        lined.end()
    }


}