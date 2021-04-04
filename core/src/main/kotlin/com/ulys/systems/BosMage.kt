package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with

class BosMage : IteratingSystem(
    allOf(
        StateComponent::class,
        AnimationComponent::class,
        Bounds::class
    ).get()
) {

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.entity {
            with<Bounds> {
                bounds.set(-5f, 0f, 10f, 12f)
            }
            with<AnimationComponent> {
                addAnimation("DEFAULT", Animation(1 / 8f, Assets.getBosMageFrames()))
            }
            with<StateComponent>()
            with<TextureComponent>()
        }
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
    }

}