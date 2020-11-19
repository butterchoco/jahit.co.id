package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_project_fragment.*

class AddProjectActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_project_fragment)

        save_project_button.setOnClickListener {
            val replyIntent = Intent()
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
                replyIntent.putExtra("project_preview", add_project_preview.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    fun isFormValidated(): Boolean {
        return !add_project_name.text?.isEmpty()!!
                && !add_project_category.text?.isEmpty()!!
                && !add_project_amount.text?.isEmpty()!!
                && !add_project_address.text?.isEmpty()!!
                && !add_project_note.text?.isEmpty()!!
                && !add_project_preview.text?.isEmpty()!!
    }

}