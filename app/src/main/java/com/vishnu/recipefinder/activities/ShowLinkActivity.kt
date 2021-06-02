package com.vishnu.recipefinder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vishnu.recipefinder.R
import kotlinx.android.synthetic.main.activity_show_link.*

class ShowLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        //Get extras
        val extras = intent.extras

        if (extras != null) {
            val link = extras.get("link")
            //Invoking WebView
            webViewId.loadUrl(link.toString())
            finish()
        } else {
            Toast.makeText(this, "Invalid Link", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}