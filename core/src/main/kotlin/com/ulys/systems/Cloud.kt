package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with

class Cloud : IteratingSystem(
    allOf(
        StateComponent::class,
        AnimationComponent::class,
        Bounds::class,
        DontCleanTag::class
    ).get()
) {

    private val right = 26f
    private val left = -right

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        val entity = engine.entity {
            with<AnimationComponent> {
                addAnimation("DEFAULT", Animation(1 / 8f, Assets.getBosMageFrames()))
            }
            with<StateComponent>()
            with<TextureComponent>()
            with<DontCleanTag>()
        }
        engine.entity {
            with<Bounds> {
                bounds.set(left - 2f, 10f, 10f, 4f)
            }
            with<Velocity> {
                speed.set(.5f, 0f)
            }
            entity.components.forEach { component -> this.entity.add(component) }
        }
        engine.entity {
            with<Bounds> {
                bounds.set(left, 11f, 13f, 2f)
            }
            with<Velocity> {
                speed.set(1.5f, 0f)
            }
            entity.components.forEach { component -> this.entity.add(component) }
        }
        engine.entity {
            with<Bounds> {
                bounds.set(left + 10f, 12f, 10f, 2f)
            }
            with<Velocity> {
                speed.set(1f, 0f)
            }
            entity.components.forEach { component -> this.entity.add(component) }
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = boundsMapper[entity] ?: return
        val vc = velocityMapper[entity] ?: return

        if (bc.bounds.x > right && vc.speed.x > 0) {
            bc.bounds.set(left - bc.bounds.width / 2f, bc.bounds.y, bc.bounds.width, bc.bounds.height)
        } else if (bc.bounds.x + bc.bounds.width < left && vc.speed.x < 0) {
            bc.bounds.set(right + bc.bounds.width / 2f, bc.bounds.y, bc.bounds.width, bc.bounds.height)
        }
    }

}