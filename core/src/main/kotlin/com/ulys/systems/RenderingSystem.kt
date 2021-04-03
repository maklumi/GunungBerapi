package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ulys.Bounds
import com.ulys.TextureComponent
import com.ulys.boundsMapper
import com.ulys.textureMapper
import ktx.ashley.allOf

class RenderingSystem : IteratingSystem(
    allOf(
        Bounds::class,
        TextureComponent::class
    ).get()
) {

    val camera = OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT)
    val batch = SpriteBatch()

    override fun processEntity(entity: Entity, deltaTime: Float) {
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.enableBlending()
        batch.begin()

        val tc = textureMapper[entity] ?: return
        val bc = boundsMapper[entity] ?: return
        val lebar = tc.region?.regionWidth?.toFloat() ?: return
        // draw lava
        batch.draw(
            tc.region,
            bc.bounds.x - bc.bounds.width / 2,
            bc.bounds.y,
            bc.bounds.width * 2,
            bc.bounds.height * 1.5f
        )
        batch.end()
    }

    companion object {
        private const val PPM = 16.0f
        var FRUSTUM_WIDTH = Gdx.graphics.width / PPM
        var FRUSTUM_HEIGHT = Gdx.graphics.height / PPM
        const val PIXELS_TO_METRES = 1.0f / PPM
        private val meterDimensions = Vector2()

        fun getScreenSizeInMeters(): Vector2 {
            meterDimensions.set(Gdx.graphics.width * PIXELS_TO_METRES, Gdx.graphics.height * PIXELS_TO_METRES)
            return meterDimensions
        }

    }
}