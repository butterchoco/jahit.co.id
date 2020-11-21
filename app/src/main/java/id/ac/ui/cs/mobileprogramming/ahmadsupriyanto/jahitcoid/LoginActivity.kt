package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.ChoiceFragment
import kotlinx.android.synthetic.main.choice_fragment.*
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.DocumentsProvider
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.add_project_fragment.*
import kotlinx.android.synthetic.main.project_detail_fragment.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChoiceFragment.newInstance(), "CHOICE_FRAGMENT")
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        customer_choice.setOnClickListener {
            val projectIntent = Intent(this, MainActivity::class.java)
            startActivity(projectIntent)
        }
    }

}