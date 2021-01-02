package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.opengl.SplashRender

class SplashSurfaceView(context: Context) : GLSurfaceView(context) {

    private var renderer: SplashRender? = null
    init {
        setEGLContextClientVersion(2)
        renderer =
            SplashRender()
        setRenderer(renderer)
    }

}