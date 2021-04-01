package com.ulys

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.math.Vector2
import ktx.ashley.*

class EntityFactory(private val engine: Engine) {

    fun addLavaBall() {
        engine.add {
            entity {
                with<Bounds>{
                    bounds.set(0f,0f,2f,5f)
                }
                with<Velocity>{
                    speed.set(Vector2(6f, -5f))
                }
            }
        }
    }

}