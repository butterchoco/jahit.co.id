package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm

import android.content.Intent
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.opengl.SplashSurfaceView

class SplashActivity : AppCompatActivity() {
    private var splashView: GLSurfaceView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashView =
            SplashSurfaceView(
                this
            )
        setContentView(splashView)
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}