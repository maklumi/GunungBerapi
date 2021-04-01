package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.Vector2
import com.ulys.Bounds
import com.ulys.Velocity
import com.ulys.boundsMapper
import com.ulys.velocityMapper
import ktx.ashley.allOf
import ktx.ashley.remove

class LavaFallSystem : IteratingSystem(
    allOf(
        Velocity::class,
        Bounds::class
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = boundsMapper[entity]
        val vel = velocityMapper[entity]
        val move = vel.speed.cpy().scl(deltaTime)
        val pos = Vector2()
        bc.bounds.getPosition(pos)
        val newPos = Vector2(pos.x + move.x, pos.y + move.y)
        bc.bounds.setPosition(newPos)
        if (newPos.y < -10f) {
            entity.remove<Velocity>()
        }
    }

}