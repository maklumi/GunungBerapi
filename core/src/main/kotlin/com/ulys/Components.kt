@file:JvmName("Components")

package com.ulys

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class Bounds : Component {
    val bounds = Rectangle()
}

val boundsMapper = mapperFor<Bounds>()


class Velocity : Component {
    val speed = Vector2()
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