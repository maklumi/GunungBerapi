package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.Rectangle
import com.ulys.Bounds
import com.ulys.CloudTag
import com.ulys.boundsMapper
import ktx.ashley.*

class CleanUpSystem : IteratingSystem(
    allOf(Bounds::class)
        .exclude(CloudTag::class).get()
) {

    private val limit = Rectangle(
        -28f, -18f,
        RenderingSystem.getScreenSizeInMeters().x - 3f,
        RenderingSystem.getScreenSizeInMeters().y - 3f
    )

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.add {
            entity {
                with<Bounds> {
                    bounds.set(limit)
                }
            }
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = boundsMapper[entity]
        if (bc.bounds.x < limit.x ||
            bc.bounds.x > limit.x + limit.width ||
            bc.bounds.y < limit.y ||
            bc.bounds.y > limit.y + limit.height
        ) {
            entity.removeAll()
            engine.removeEntity(entity)
        }
    }
}