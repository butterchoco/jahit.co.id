package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.add_movie_fragment.*

class AddProjectActivity : AppCompatActivity() {

    var uploadedImage = ""
    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    var PROCESS_WAIT = ""

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_movie_fragment)
        add_movie__preview.visibility = View.GONE

        add_movie_upload_preview.setOnClickListener {
            if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
                onPickPhoto()
            }
            PROCESS_WAIT = "UPLOAD_PREVIEW"
        }

        save_movie_button.setOnClickListener {
            val replyIntent = Intent()
            if (!isFormValidated()) {
                Toast.makeText(
                    applicationContext,
                    R.string.movie_add_empty_field_message,
                    Toast.LENGTH_LONG).show()
            } else {
                replyIntent.putExtra("movie_name", add_movie_name.text.toString())
                replyIntent.putExtra("movie_category", add_movie_category.text.toString())
                replyIntent.putExtra("movie_amount", add_movie_amount.text.toString())
                replyIntent.putExtra("movie_address", add_movie_address.text.toString())
                replyIntent.putExtra("movie_note", add_movie_note.text.toString())
                replyIntent.putExtra("movie_preview", uploadedImage)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if ((resultData != null) && requestCode == PICK_PHOTO_CODE) {
            uploadedImage = resultData.data.toString()
            add_movie__preview.text = uploadedImage
            add_movie__preview.visibility = View.VISIBLE
        }
    }

    fun isFormValidated(): Boolean {
        return !add_movie_name.text?.isEmpty()!!
                && !add_movie_category.text?.isEmpty()!!
                && !add_movie_amount.text?.isEmpty()!!
                && !add_movie_address.text?.isEmpty()!!
                && !add_movie_note.text?.isEmpty()!!
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

    fun showDialog(
        msg: String, context: Context?,
        permission: String
    ) {
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle(R.string.permission_ask_title)
        alertBuilder.setMessage(msg + " " + applicationContext.resources.getString(R.string.permission_ask_content))
        alertBuilder.setPositiveButton(
            R.string.yes,
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    ActivityCompat.requestPermissions(
                        context as Activity, arrayOf(permission),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
            })
        val alert: AlertDialog = alertBuilder.create()
        alert.show()
    }

    fun checkPermissionREAD_EXTERNAL_STORAGE(
        context: Context
    ): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog(applicationContext.resources.getString(R.string.external_storage_text), context, Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    ActivityCompat.requestPermissions(context as Activity, arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                false
            } else {
                true
            }
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (PROCESS_WAIT == "UPLOAD_PREVIEW") {
                    onPickPhoto()
                }
                return;
            } else {
                Toast.makeText(
                    this, R.string.permission_denied,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> super.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        }
    }

    companion object {
        const val PICK_PHOTO_CODE = 1046
    }

}