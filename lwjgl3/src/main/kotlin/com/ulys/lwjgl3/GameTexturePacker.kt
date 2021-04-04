package com.ulys.lwjgl3

import com.badlogic.gdx.tools.texturepacker.TexturePacker


object GameTexturePacker {
    private const val INPUT_DIR = "lwjgl3/raw-assets/ombak"
    private const val OUTPUT_DIR = "assets/animations/"
    private const val PACK_FILE = "ombak"

    @JvmStatic
    fun main(args: Array<String>) {
        // create the packing's settings
        val settings = TexturePacker.Settings()

        // adjust the padding settings
        settings.scale = floatArrayOf(1f)
        settings.paddingX = 2
        settings.paddingY = 2
        settings.edgePadding = false
        settings.maxWidth = 2048
        settings.maxHeight = 2048

        // pack the images
        settings.combineSubdirectories = true
        TexturePacker.process(settings, INPUT_DIR, OUTPUT_DIR, PACK_FILE)
        settings.combineSubdirectories = true
    }
}

