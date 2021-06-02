package com.vishnu.recipefinder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.vishnu.recipefinder.R
import com.vishnu.recipefinder.data.RecipeListAdapter
import com.vishnu.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject

class RecipeList : AppCompatActivity() {
    var volleyRequest: RequestQueue? = null //Initializing a request queue variable
    var recipeList: ArrayList<Recipe>? = null //Initializing array list variable
    var recipeListAdapter: RecipeListAdapter? = null //Adapter
//    var layoutManager: RecyclerView.LayoutManager? = null //LayoutManager

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

    private fun getRecipe(Url: String) {
        val recipeRequest = JsonObjectRequest(Request.Method.GET, Url,
            {
                response: JSONObject ->
                    try {
                        val resultArray = response.getJSONArray("results")
                        //Looping through the array
                        for (i in 0 until resultArray.length() -1) {
                            val recipeObject = resultArray.getJSONObject(i)

                            val recipe = Recipe()
                            recipe.title = recipeObject.getString("title")
                            recipe.link = recipeObject.getString("href")
                            recipe.ingredients = recipeObject.getString("ingredients")
                            recipe.thumbnail = recipeObject.getString("thumbnail")

                            Log.d("Response ===>", recipe.title.toString())
                            recipeList!!.add(recipe)

//                            Instantiating RecipeListAdapter
                            recipeListAdapter = RecipeListAdapter(recipeList!!, this)
//                            layoutManager = LinearLayoutManager(this)

                            //RecyclerView setup
                            recyclerViewId.layoutManager = LinearLayoutManager(this)
                            recyclerViewId.adapter = recipeListAdapter
                        }
                        recipeListAdapter!!.notifyDataSetChanged()
                    } catch (e: JSONException) {e.printStackTrace()}
            }, //Response.Listener
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