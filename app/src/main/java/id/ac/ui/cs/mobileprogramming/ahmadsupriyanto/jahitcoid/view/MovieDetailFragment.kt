package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import kotlinx.android.synthetic.main.project_detail_fragment.*

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            MovieDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle? = getArguments();
        if (bundle != null) {
                project_detail_name.text = bundle.getString("project_name")
                project_detail_price.text = bundle.getString("project_price")
                project_detail_amount.text = bundle.getString("project_amount")
                project_detail_category.text = bundle.getString("project_category")
                project_detail_quotation.text = bundle.getString("project_quotation")
                project_detail_vendor.text = bundle.getString("project_vendor")
                project_detail_start_date.text = bundle.getString("project_startDate")
                project_detail_end_date.text = bundle.getString("project_endDate")
                project_detail_address.text = bundle.getString("project_address")
                project_detail_note.text = bundle.getString("project_note")
                Log.d("--------------------",bundle.getString("project_preview"))
                val selectedImage: Bitmap? = loadFromUri(bundle.getString("project_preview").toString());
                project_detail_preview.setImageBitmap(selectedImage);
        }
    }

    fun loadFromUri(photoUri: String): Bitmap? {
        var image: Bitmap = MediaStore.Images.Media.getBitmap(
            activity?.getContentResolver()!!, Uri.parse(photoUri));
         val bitmap = ThumbnailUtils.extractThumbnail(image,240,240);

        return bitmap
    }

}