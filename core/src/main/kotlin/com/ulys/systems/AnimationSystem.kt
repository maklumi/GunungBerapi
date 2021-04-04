package com.ulys.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.ulys.*
import ktx.ashley.allOf

class AnimationSystem : IteratingSystem(
    allOf(
        TextureComponent::class,
        AnimationComponent::class,
        StateComponent::class
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val ac = animationMapper[entity]
        val sc = stateMapper[entity]
        val tc = textureMapper[entity]
        val region = ac.animations.get(sc.state)?.getKeyFrame(sc.time, sc.isLooping)
        tc.region = region
        sc.time += deltaTime
    }

}