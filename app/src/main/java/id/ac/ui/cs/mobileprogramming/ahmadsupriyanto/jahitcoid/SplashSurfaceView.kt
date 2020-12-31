package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.opengldiscovery

import android.content.Context
import android.opengl.GLSurfaceView

class SplashSurfaceView(context: Context) : GLSurfaceView(context) {

    private var renderer: SplashRender? = null
    init {
        setEGLContextClientVersion(2)
        renderer = SplashRender()
        setRenderer(renderer)
    }

}