package com.vishnu.recipefinder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.vishnu.recipefinder.R
import com.vishnu.recipefinder.model.Recipe
import org.json.JSONException
import org.json.JSONObject

class RecipeList : AppCompatActivity() {
    var volleyRequest: RequestQueue? = null //Initializing a request queue variable
    var recipeList: ArrayList<Recipe>? = null //Initializing array list variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        //API URL String
        val urlString = "http://www.recipepuppy.com/api"
        volleyRequest = Volley.newRequestQueue(this) //Assigning a new request queue
        recipeList = ArrayList<Recipe>() //Array list

        //Function call to get recipes from API
        getRecipe(urlString)
    }

    fun getRecipe(Url: String) {
        val recipeRequest = JsonObjectRequest(Request.Method.GET, Url,
            {
                response: JSONObject ->
                    try {
                        val resultArray = response.getJSONArray("results")
                        //Looping through the array
                        for (i in 0 until resultArray.length() -1) {
                            var recipeObject = resultArray.getJSONObject(i)

                            //Retrieving all object properties
                            var title = recipeObject.getString("title")
                            var link = recipeObject.getString("href")
                            var ingredients = recipeObject.getString("ingredients")
                            var thumbnail = recipeObject.getString("thumbnail")

                            Log.d("Response ===>", title)

                            //NEED TO COMBINE CODE TO HAVE EFFICIENT CODE
                            val recipe = Recipe()
                            recipe.title = title
                            recipe.link = link
                            recipe.ingredients = ingredients
                            recipe.thumbnail = thumbnail

                            recipeList!!.add(recipe)

                        }
                    } catch (e: JSONException) {e.printStackTrace()}
            }, //Reponse.Listener
            {
                error: VolleyError? ->
                    try {
                        Log.d("Error ==>", error.toString())
                    } catch (e: JSONException) {e.printStackTrace()}
            } //Response.ErrorListener
        )
        volleyRequest!!.add(recipeRequest)
    }
}