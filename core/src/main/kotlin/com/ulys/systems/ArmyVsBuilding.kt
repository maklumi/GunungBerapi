package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.utils.Array
import com.ulys.*
import ktx.ashley.has
import ktx.ashley.oneOf

class ArmyVsBuilding : IteratingSystem(oneOf(ArmyTag::class, BuildingTag::class).get()) {

    private val units = Array<Entity>()
    private val buildings = Array<Entity>()

    override fun processEntity(entity: Entity, deltaTime: Float) {
        if (entity.has(buildingTag)) {
            buildings.add(entity)
        } else if (entity.has(armyTag)) {
            units.add(entity)
        }
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)
        for (building in buildings) {
            val buildingBound = boundsMapper[building]
            for (army in units) {
                val armyBound = boundsMapper[army]
                if (armyBound.bounds.overlaps(buildingBound.bounds)) {
                    val vc = velocityMapper[army]
                    vc.speed.x = 0f
                }
            }
        }
        buildings.clear()
        units.clear()
    }
}