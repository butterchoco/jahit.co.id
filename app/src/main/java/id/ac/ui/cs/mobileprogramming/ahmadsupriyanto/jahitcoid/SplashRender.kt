package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SplashRender : GLSurfaceView.Renderer {
    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)
    private val rotationMatrix = FloatArray(16)
    private lateinit var textureCube: TextureCube

    override fun onSurfaceCreated(
        gl: GL10,
        eglConfig: EGLConfig
    ) {
        textureCube = TextureCube()
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
    }

    override fun onDrawFrame(gl: GL10) {
        val scratch = FloatArray(16)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        val time = SystemClock.uptimeMillis() % 4000L
        val angle = 0.090f * time.toInt()
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 90f, -1.0f)
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0)
        textureCube.draw(scratch)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val ratio = width.toFloat() / height
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    companion object {
        @JvmStatic
        fun loadShader(glVertexShader: Int, vertexShaderCode: String): Int {
            val shader = GLES20.glCreateShader(glVertexShader)
            GLES20.glShaderSource(shader, vertexShaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }
    }
}