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
}