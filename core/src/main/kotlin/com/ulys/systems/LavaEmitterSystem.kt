package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ArrayMap
import com.ulys.*
import ktx.ashley.add
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with

class LavaEmitterSystem : IteratingSystem(allOf(LavaEmitterComponent::class).get()) {

    private val emitterMap = ArrayMap<Int, Array<Entity>>()
    private val charges = ArrayMap<Int, Float>()

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.entity {
            with<LavaEmitterComponent> {
                triggerKey = Input.Keys.F
                emissionVelocity = Vector2(-6f, -5f)
            }
        }
        engine.entity {
            with<LavaEmitterComponent> {
                triggerKey = Input.Keys.J
                emissionVelocity = Vector2(6f, -5f)
            }
        }
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        for (key in emitterMap.keys()) {

            if (ActionProcessor.isKeyJustReleased(key)) {
                val currentCharge = charges.get(key)
                for (entiti in emitterMap[key]) {
                    val lec = lavaEmitterMapper[entiti]
                    val emm = lec.emissionVelocity.cpy()
                    addLavaBall(Vector2(emm.x * currentCharge, emm.y))
                    charges.put(key, 0f)
                }
                ActionProcessor.clear()
            } else if (ActionProcessor.isKeyDown(key)) {
                var currentCharge = charges.get(key)
                currentCharge = 2f.coerceAtMost(currentCharge + 6f * deltaTime)
                charges.put(key, currentCharge)
            }
            emitterMap[key].clear()
        }

    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val lec = lavaEmitterMapper[entity]
        if (!emitterMap.containsKey(lec.triggerKey)) emitterMap.put(lec.triggerKey, Array())
        if (!charges.containsKey(lec.triggerKey)) charges.put(lec.triggerKey, 0.1f)

        emitterMap.get(lec.triggerKey).add(entity)
    }

    private fun addLavaBall(vel: Vector2) {
        engine.add {
            entity {
                with<Bounds> {
                    bounds.set(0f, 10f, 2f, 5f)
                }
                with<Velocity> {
                    speed.set(vel)
                }
                with<LavaBallTag>()
                with<TextureComponent>()
                with<AnimationComponent> {
                    addAnimation("DEFAULT", Animation<TextureRegion>(1 / 16f, Assets.getLavaBallFrames()))
                }
                with<StateComponent>()
            }
        }
    }
}