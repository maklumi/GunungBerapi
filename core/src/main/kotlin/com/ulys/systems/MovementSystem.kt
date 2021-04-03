package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.Vector2
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.exclude
import ktx.ashley.has
import ktx.ashley.remove

class MovementSystem : IteratingSystem(
    allOf(
        Velocity::class,
        Bounds::class
    ).exclude(Kinematic::class)
        .get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = boundsMapper[entity]
        val vel = velocityMapper[entity]
        val move = vel.speed.cpy().scl(deltaTime)
        val pos = Vector2()
        bc.bounds.getPosition(pos)
        val newPos = Vector2(pos.x + move.x, pos.y + move.y)
        bc.bounds.setPosition(newPos)
//        if (entity.has(lavaBallTag)) {
//            if (newPos.y < -10f) {
//                entity.remove<Velocity>()
//            }
//        }
    }

}
