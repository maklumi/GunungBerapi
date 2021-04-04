package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with

class Ombak : IteratingSystem(
    allOf(
        StateComponent::class,
        AnimationComponent::class,
        Bounds::class,
        DontCleanTag::class
    ).get()
) {


    private val lebar = RenderingSystem.getScreenSizeInMeters().x
    private val tinggi = RenderingSystem.getScreenSizeInMeters().y
    private val rect = Rectangle(-lebar / 2, -tinggi / 2, lebar, tinggi)

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.entity {
            with<Bounds> {
                bounds.set(rect.x, rect.y, rect.width, rect.height / 3)
            }
            with<AnimationComponent> {
                addAnimation("DEFAULT", Animation(1 / 4f, Assets.getOmbakFrames()))
            }

            with<StateComponent>()
            with<TextureComponent>()
            with<DontCleanTag>()
        }
        engine.entity {
            with<Bounds> {
                bounds.set(rect.x, rect.y, rect.width, rect.height / 4)
            }
            with<AnimationComponent> {
                val frames = Assets.getOmbakFrames()
                val temps = Array<TextureAtlas.AtlasRegion>()
                for (f in frames) {
                    f.flip(true, false)
                    temps.add(f)
                }
                addAnimation("DEFAULT", Animation(1 / 4f, temps))
            }

            with<StateComponent>()
            with<TextureComponent>()
            with<DontCleanTag>()
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
    }

}