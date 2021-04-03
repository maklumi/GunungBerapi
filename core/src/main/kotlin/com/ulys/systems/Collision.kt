package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.utils.Array
import com.ulys.*
import ktx.ashley.addComponent
import ktx.ashley.has
import ktx.ashley.oneOf
import ktx.ashley.remove

class Collision : IteratingSystem(
    oneOf(
        ArmyTag::class, BuildingTag::class, LavaBallTag::class
    ).get()
) {

    private val units = Array<Entity>()
    private val buildings = Array<Entity>()
    private val lavaballs = Array<Entity>()
    private var buildingCounts = 5
    private var armyCounts = 5

    override fun processEntity(entity: Entity, deltaTime: Float) {
        when {
            entity.has(buildingTag) -> buildings.add(entity)
            entity.has(armyTag) -> units.add(entity)
            entity.has(lavaBallTag) -> lavaballs.add(entity)
        }
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        if (armyCounts < 0 || buildingCounts < 0) {
            setProcessing(false)
            val armySpawnerSystem = engine.getSystem(ArmySpawnerSystem::class.java)
            engine.removeSystem(armySpawnerSystem)
        }

        for (army in units) {
            val armyBound = boundsMapper[army]
            for (building in buildings) {
                val buildingBound = boundsMapper[building] ?: continue
                if (armyBound.bounds.overlaps(buildingBound.bounds)) {
                    army.addComponent<Kinematic>(engine)

                    val hc = healthMapper[building] ?: continue
                    hc.health -= (deltaTime * 0.1f)
                    if (hc.health < 0f) {
                        hc.health = 1f
                        building.removeAll()
                        buildingCounts--
                    }
                } else {
                    army.remove<Kinematic>()
                }
            }
            for (ball in lavaballs) {
                val ballbound = boundsMapper[ball]
                if (armyBound.bounds.overlaps(ballbound.bounds)) {
                    army.addComponent<Kinematic>(engine)

                    val hc = healthMapper[army] ?: continue
                    hc.health -= (deltaTime * 0.5f)
                    if (hc.health < 0f) {
                        hc.health = 1f
                        army.removeAll()
                        armyCounts--
                    }
                } else {
                    army.remove<Kinematic>()
                }
            }
        }
        lavaballs.clear()
        buildings.clear()
        units.clear()
    }
}