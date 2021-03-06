@file:JvmName("Components")

package com.ulys

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.ArrayMap
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class Bounds : Component {
    val bounds = Rectangle()
}

val boundsMapper = mapperFor<Bounds>()


class Velocity : Component, Pool.Poolable {
    val speed = Vector2()

    override fun reset() {
        speed.set(Vector2())
    }
}

val velocityMapper = mapperFor<Velocity>()

class LavaEmitterComponent : Component {
    var emissionVelocity = Vector2()
    var triggerKey = Input.Keys.ANY_KEY
}

val lavaEmitterMapper = mapperFor<LavaEmitterComponent>()

class ArmySpawner : Component {
    var direction = 1
    var armySpeed = 2f
    var isActive = false
    var interval = 5f
    var elapsedTime = 0f
}

val armySpawnerMapper = mapperFor<ArmySpawner>()

class Health : Component {
    var health = 1f
    var maxHealth = 1f
}

val healthMapper = mapperFor<Health>()

class TextureComponent : Component, Pool.Poolable {
    var region: TextureRegion? = null

    override fun reset() {
        region = null
    }
}

val textureMapper = mapperFor<TextureComponent>()

class AnimationComponent : Component {
    var animations = ArrayMap<String, Animation<TextureRegion>>()

    fun addAnimation(stateName: String, animation: Animation<TextureRegion>): AnimationComponent {
        animations.put(stateName, animation)
        return this
    }
}

val animationMapper = mapperFor<AnimationComponent>()

class StateComponent : Component, Pool.Poolable {
    var state = "DEFAULT"
    var time = 1 / 16f
    var isLooping = true

    override fun reset() {
        state = "DEFAULT"
        time = 1 / 16f
        isLooping = true
    }
}

val stateMapper = mapperFor<StateComponent>()