@file:JvmName("ComponentTag")

package com.ulys

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class LavaBallTag : Component

val lavaBallTag = mapperFor<LavaBallTag>()

class ArmyTag : Component

val armyTag = mapperFor<ArmyTag>()

class BuildingTag : Component

val buildingTag = mapperFor<BuildingTag>()