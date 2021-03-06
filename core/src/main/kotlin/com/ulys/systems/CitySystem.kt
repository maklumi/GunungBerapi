package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with

class CitySystem : IteratingSystem(allOf(BuildingTag::class).get()) {

    private val buildings = Array<Entity>()
    private var isInitialized = false

    override fun processEntity(entity: Entity, deltaTime: Float) {
        buildings.add(entity)
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)
        if (!isInitialized) {
            addInitialBuildings()
        }
        buildings.clear()
        setProcessing(false)
    }

    private fun addInitialBuildings() {
        isInitialized = true
        addBuildingComponent(0f, -10f, 2f, 3f)
        addBuildingComponent(3f, -8f, 2f, 2f)
        addBuildingComponent(4f, -5f, 2f, 2f)
        addBuildingComponent(-1f, -3f, 2f, 2f)
        addBuildingComponent(-4f, -5f, 2f, 2f)
        addBuildingComponent(-7f, -3f, 2f, 2f)
        addBuildingComponent(-7f, -7f, 3f, 2f)
    }

    private fun addBuildingComponent(x: Float, y: Float, boundW: Float, boundH: Float) {
        engine.entity {
            with<BuildingTag>()
            with<Bounds> {
                bounds.set(x, y, boundW, boundH)
            }
            with<Health>()
            with<TextureComponent>()
            with<StateComponent>()
            with<AnimationComponent> {
                addAnimation("DEFAULT", Animation<TextureRegion>(1 / 4f, Assets.getDiscipleFrames()))
                addAnimation("DEAD", Animation<TextureRegion>(1 / 7f, Assets.getDiscipleDeadFrames()))
            }
        }
    }
}