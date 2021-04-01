package com.ulys

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.utils.ArrayMap

class ActionProcessor(vararg keys: Int) : InputAdapter() {

    init {
        for (key in keys) {
            keyStates.put(key, KeyState())
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keyStates.containsKey(keycode)) {
            keyStates[keycode].isPressed = true
        }
        return super.keyDown(keycode)
    }

    override fun keyUp(keycode: Int): Boolean {
        if (keyStates.containsKey(keycode)) {
            keyStates[keycode].isJustReleased = true
        }
        return super.keyDown(keycode)
    }

    companion object {
        class KeyState {
            var isPressed = false
            var isJustReleased = false
        }

        val keyStates = ArrayMap<Int, KeyState>()

        fun isKeyDown(key: Int): Boolean {
            return if (keyStates.containsKey(key)) {
                keyStates[key].isPressed
            } else false
        }

        fun isKeyJustReleased(key: Int): Boolean {
            return if (keyStates.containsKey(key)) {
                keyStates[key].isJustReleased
            } else false
        }

        fun clear() {
            for (key in keyStates.keys()) {
                keyStates[key].isPressed = false
                keyStates[key].isJustReleased = false
            }
        }
    }

}