package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm

import android.opengl.GLES20
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.SplashRender.Companion.loadShader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class TextureCube {
    private val vertexBuffer: FloatBuffer
    private val indexBuffer: ShortBuffer
    private val colors = arrayOf(
        floatArrayOf(1.0f, 0.5f, 0.0f, 1.0f),
        floatArrayOf(1.0f, 0.0f, 1.0f, 1.0f),
        floatArrayOf(0.0f, 1.0f, 0.0f, 1.0f),
        floatArrayOf(0.0f, 0.0f, 1.0f, 1.0f),
        floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f),
        floatArrayOf(1.0f, 1.0f, 0.0f, 1.0f)
    )
    var indices = shortArrayOf(
        0, 1, 2, 2,
        1, 3, 5, 4,
        7, 7, 4, 6,
        8, 9, 10, 10,
        9, 11, 12, 13,
        14, 14, 13, 15,
        16, 17, 18, 18,
        17, 19, 22, 23,
        20, 20, 23, 21
    )
    private val vertexShaderCode = "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}"
    private val fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}"
    private val program: Int
    private var positionHandle = 0
    private var colorHandle = 0
    private val vertexCount =
        vertices.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4
    private var vPMatrixHandle = 0
    private val numFaces = 6
    fun draw(mvpMatrix: FloatArray?) {
        GLES20.glUseProgram(program)
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(
            positionHandle,
            COORDS_PER_VERTEX,
            GLES20.GL_FLOAT,
            false,
            vertexStride,
            vertexBuffer
        )
        vPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix")
        colorHandle = GLES20.glGetUniformLocation(program, "vColor")
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)
        for (face in 0 until numFaces) {
            GLES20.glUniform4fv(colorHandle, 1, colors[face], 0)
            indexBuffer.position(face * 6)
            GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, indexBuffer)
        }
        GLES20.glDisableVertexAttribArray(positionHandle)
    }

    companion object {
        const val COORDS_PER_VERTEX = 3
        var vertices = floatArrayOf(
            -0.2f, -0.2f, 0.2f,  // 0. left-bottom-front
            0.2f, -0.2f, 0.2f,  // 1. right-bottom-front
            -0.2f, 0.2f, 0.2f,  // 2. left-top-front
            0.2f, 0.2f, 0.2f,  // 3. right-top-front
            // BACK
            0.2f, -0.2f, -0.2f,  // 6. right-bottom-back
            -0.2f, -0.2f, -0.2f,  // 4. left-bottom-back
            0.2f, 0.2f, -0.2f,  // 7. right-top-back
            -0.2f, 0.2f, -0.2f,  // 5. left-top-back
            // LEFT
            -0.2f, -0.2f, -0.2f,  // 4. left-bottom-back
            -0.2f, -0.2f, 0.2f,  // 0. left-bottom-front
            -0.2f, 0.2f, -0.2f,  // 5. left-top-back
            -0.2f, 0.2f, 0.2f,  // 2. left-top-front
            // RIGHT
            0.2f, -0.2f, 0.2f,  // 1. right-bottom-front
            0.2f, -0.2f, -0.2f,  // 6. right-bottom-back
            0.2f, 0.2f, 0.2f,  // 3. right-top-front
            0.2f, 0.2f, -0.2f,  // 7. right-top-back
            // TOP
            -0.2f, 0.2f, 0.2f,  // 2. left-top-front
            0.2f, 0.2f, 0.2f,  // 3. right-top-front
            -0.2f, 0.2f, -0.2f,  // 5. left-top-back
            0.2f, 0.2f, -0.2f,  // 7. right-top-back
            // BOTTOM
            -0.2f, -0.2f, -0.2f,  // 4. left-bottom-back
            0.2f, -0.2f, -0.2f,  // 6. right-bottom-back
            -0.2f, -0.2f, 0.2f,  // 0. left-bottom-front
            0.2f, -0.2f, 0.2f // 1. right-bottom-front
        )
    }

    init {
        val vbb =
            ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder()) // Use native byte order
        vertexBuffer = vbb.asFloatBuffer() // Convert from byte to float
        vertexBuffer.put(vertices) // Copy data into buffer
        vertexBuffer.position(0) // Rewind

        // initialize byte buffer for the draw list
        indexBuffer = ByteBuffer.allocateDirect(indices.size * 2)
            .order(ByteOrder.nativeOrder()).asShortBuffer()
        indexBuffer.put(indices).position(0)
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader =
            loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
        program = GLES20.glCreateProgram()
        GLES20.glAttachShader(program, vertexShader)
        GLES20.glAttachShader(program, fragmentShader)
        GLES20.glLinkProgram(program)
    }
}