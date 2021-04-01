package com.ulys.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera

class RenderingSystem : EntitySystem() {

    val camera = OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT)

    companion object {
        private const val PPM = 16.0f
        var FRUSTUM_WIDTH = Gdx.graphics.width / PPM
        var FRUSTUM_HEIGHT = Gdx.graphics.height / PPM
    }
}