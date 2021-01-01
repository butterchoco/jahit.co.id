package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(var bmImage: ImageView) :
    AsyncTask<String?, Void?, Bitmap?>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        val urldisplay = params[0]
        var picture: Bitmap? = null
        try {
            val `in` = URL(urldisplay).openStream()
            picture = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error Download", e.message)
            e.printStackTrace()
        }
        return picture
    }

    override fun onPostExecute(result: Bitmap?) {
        bmImage.setImageBitmap(result)
    }

}