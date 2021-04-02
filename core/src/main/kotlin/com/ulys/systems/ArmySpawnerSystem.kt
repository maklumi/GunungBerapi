package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.utils.Array
import com.ulys.*
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.with
import java.util.*

class ArmySpawnerSystem : IteratingSystem(allOf(ArmySpawner::class, ArmyTag::class).get()) {

    private val armySpawners = Array<Entity>()
    private val random = Random()
    private var count = 5

    override fun update(deltaTime: Float) {
        super.update(deltaTime)
        if (count == 0) return
        for (spawner in armySpawners) {
            val asc = armySpawnerMapper[spawner]
            asc.elapsedTime += deltaTime
            if (asc.elapsedTime > asc.interval) {
                addArmyOrCavalry(spawner)
                asc.elapsedTime = 0f
                count--
            }
        }
        armySpawners.clear()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val asc = armySpawnerMapper[entity]
        if (asc.isActive) armySpawners.add(entity)
    }

    private fun addArmyOrCavalry(spawner: Entity) {
        val asc = armySpawnerMapper[spawner]
        val bc = boundsMapper[spawner]
        val cavalry = random.nextFloat() > .50f
        if (cavalry) {
            engine.entity {
                with<Bounds> {
                    bounds.setPosition(bc.bounds.x, bc.bounds.y)
                    bounds.setSize(6f, 6f)
                }
                with<Velocity> {
                    speed.x = asc.direction * asc.armySpeed
                }
                with<ArmyTag>()
                with<Health>()
            }
        } else {
            engine.entity {
                with<Bounds> {
                    bounds.setPosition(bc.bounds.x, bc.bounds.y)
                    bounds.setSize(4f, 4f)
                }
                with<Velocity> {
                    speed.x = asc.direction * asc.armySpeed
                }
                with<ArmyTag>()
                with<Health>()
            }
        }


    }

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.entity {
            with<ArmySpawner> {
                isActive = true
                direction = -1
            }
            with<Bounds> {
                bounds.setPosition(25f, -9f)
            }
            with<ArmyTag>()
        }
        engine.entity {
            with<ArmySpawner> {
                isActive = true
                direction = 1
            }
            with<Bounds> {
                bounds.setPosition(-25f, -8f)
            }
            with<ArmyTag>()
        }
    }
}