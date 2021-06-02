package com.vishnu.recipefinder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vishnu.recipefinder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            val intent = Intent(this, RecipeList::class.java)

            //Getting user input
            val ingredients = ingredientsEditText.text.toString().trim()
            val searchTerm = searchTermEditText.text.toString().trim()

            intent.putExtra("ingredients", ingredients)
            intent.putExtra("search", searchTerm)

            Log.d("Ingredients ===> ", ingredients)
            Log.d("SearchTerm ===> ", searchTerm)

            startActivity(intent)
        }
    }
}