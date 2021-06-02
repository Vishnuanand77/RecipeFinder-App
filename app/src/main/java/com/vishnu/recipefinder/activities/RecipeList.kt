package com.vishnu.recipefinder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.vishnu.recipefinder.R

class RecipeList : AppCompatActivity() {
    var volleyRequest: RequestQueue? = null //Initializing a request queue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        volleyRequest = Volley.newRequestQueue(this) //Assigning a new request queue
    }
}