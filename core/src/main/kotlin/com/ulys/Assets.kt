package com.ulys

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

object Assets {

    private val am = AssetManager()

    fun load() {
        am.setLoader(Texture::class.java, TextureLoader(InternalFileHandleResolver()))
        am.load("sprites/fireball.bmp", Texture::class.java)
        am.load("sprites/andromalius-57x88.png", Texture::class.java)
        am.load("sprites/minion-45x66.png", Texture::class.java)
        am.load("sprites/disciple-45x51.png", Texture::class.java)
        am.load("sprites/mage-1-85x94.png", Texture::class.java)
        am.finishLoading()
    }

    fun getLavaBallFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/fireball.bmp")
        val frames = TextureRegion.split(texture, 7, 12)
        val animFrames = Array<TextureRegion>(5)
        for (row in frames) {
            for (col in row) {
                col.flip(false, true)
                animFrames.add(col)
            }
        }
        return animFrames
    }

    fun getAndromaliusFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/andromalius-57x88.png")
        val frames = TextureRegion.split(texture, 456 / 8, 264 / 3)
        val animFrames = Array<TextureRegion>(frames.size)
        for (col in frames[1]) {
            animFrames.add(col)
        }
        return animFrames
    }

    fun getMinionFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/minion-45x66.png")
        val frames = TextureRegion.split(texture, 135 / 3, 132 / 2)
        val animFrames = Array<TextureRegion>(frames.size)
        for (col in frames[0]) {
            animFrames.add(col)
        }
        return animFrames
    }

    fun getDiscipleFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/disciple-45x51.png")
        val frames = TextureRegion.split(texture, 45, 51)
        val animFrames = Array<TextureRegion>(frames.size)
        for (col in frames[0]) {
            animFrames.add(col)
        }
        return animFrames
    }

    fun getDiscipleDeadFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/disciple-45x51.png")
        val frames = TextureRegion.split(texture, 45, 51)
        val animFrames = Array<TextureRegion>(7)
        for (col in frames[1]) {
            animFrames.add(col)
        }
        for (i in 0..2) {
            animFrames.add(frames[2][i])
        }
        return animFrames
    }

    fun getBosMageFrames(): Array<TextureRegion> {
        val texture = am.get<Texture>("sprites/mage-1-85x94.png")
        val frames = TextureRegion.split(texture, 85, 94)
        val animFrames = Array<TextureRegion>(8)
        for (col in frames) {
            for (row in col) {
                animFrames.add(row)
            }
        }
        return animFrames
    }
}