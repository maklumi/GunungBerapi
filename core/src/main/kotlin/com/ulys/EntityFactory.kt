package com.ulys

import com.badlogic.ashley.core.Engine
import ktx.ashley.*

class EntityFactory(private val engine: Engine) {

    fun addEntiti() {
        engine.add {
            entity {
                with<Bounds>{
                    bounds.set(0f,0f,2f,5f)
                }
            }
        }
    }

}