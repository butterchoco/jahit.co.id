package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.DocumentsProvider
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.add_project_fragment.*
import kotlinx.android.synthetic.main.project_detail_fragment.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddProjectActivity : AppCompatActivity() {

    var uploadedImage = ""

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_project_fragment)

        add_project_preview.setOnClickListener {
            onPickPhoto()
        }

        save_project_button.setOnClickListener {
            val replyIntent = Intent()
            Log.d("---------", uploadedImage)
            if (!isFormValidated()) {
                Toast.makeText(
                    applicationContext,
                    R.string.project_add_empty_field_message,
                    Toast.LENGTH_LONG).show()
            } else {
                replyIntent.putExtra("project_name", add_project_name.text.toString())
                replyIntent.putExtra("project_category", add_project_category.text.toString())
                replyIntent.putExtra("project_amount", add_project_amount.text.toString())
                replyIntent.putExtra("project_address", add_project_address.text.toString())
                replyIntent.putExtra("project_note", add_project_note.text.toString())
                replyIntent.putExtra("project_preview", uploadedImage)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if ((resultData != null) && requestCode == PICK_PHOTO_CODE) {
            uploadedImage = resultData.data.toString()
        }
    }

    fun isFormValidated(): Boolean {
        return !add_project_name.text?.isEmpty()!!
                && !add_project_category.text?.isEmpty()!!
                && !add_project_amount.text?.isEmpty()!!
                && !add_project_address.text?.isEmpty()!!
                && !add_project_note.text?.isEmpty()!!
                && !uploadedImage.isEmpty()
    }

    fun onPickPhoto() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_PHOTO_CODE)
        }
    }

    companion object {
        const val PICK_PHOTO_CODE = 1046
    }

}