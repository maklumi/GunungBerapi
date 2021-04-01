@file:JvmName("Components")

package com.ulys

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Rectangle
import ktx.ashley.mapperFor

class Bounds : Component {
    val bounds = Rectangle()
}

val boundsMapper = mapperFor<Bounds>()