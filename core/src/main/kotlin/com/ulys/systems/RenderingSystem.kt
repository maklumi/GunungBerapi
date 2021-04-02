package com.ulys.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2

class RenderingSystem : EntitySystem() {

    val camera = OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT)

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