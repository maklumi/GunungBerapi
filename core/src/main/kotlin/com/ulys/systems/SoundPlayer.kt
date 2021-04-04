package com.ulys.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.audio.Sound
import com.ulys.Assets

class SoundPlayer : EntitySystem() {

    enum class Sonar {
        LAVA,
        TIADA
    }

    private val lava: Sound = Assets.getBunyiLava()

    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)
        setProcessing(false)
    }

    fun prosesBunyi(bunyi: Sonar) {
        when (bunyi) {
            Sonar.LAVA -> {
                lava.play()
            }
            Sonar.TIADA -> {

            }
        }
    }
}