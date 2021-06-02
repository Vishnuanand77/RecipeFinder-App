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
import com.vishnu.recipefinder.model.LEFT_LINK
import com.vishnu.recipefinder.model.QUERY
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
        val url: String?

        val extras = intent.extras //Getting information from the previous page
        val ingredientsCheck = extras?.get("ingredients")
        val searchCheck = extras?.get("search")

        if (ingredientsCheck!! == "" && searchCheck!! == "") {
            //Construct customized Url
            val tempUrl = LEFT_LINK + "ingredientsCheck" + QUERY + searchCheck
            url = tempUrl
            Log.d("TEMP URL ===>", tempUrl)
        } else {
            url = "http://www.recipepuppy.com/api"
        }

        volleyRequest = Volley.newRequestQueue(this) //Assigning a new request queue
        recipeList = ArrayList<Recipe>() //Array list

        //Function call to get recipes from API
        getRecipe(url)
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

                            val title = recipeObject.getString("title")
                            val link = recipeObject.getString("href")
                            val thumbnail = recipeObject.getString("thumbnail")
                            val ingredients = recipeObject.getString("ingredients")

                            val recipe = Recipe()
                            recipe.title = title
                            recipe.link = link
                            recipe.ingredients = "Ingredients: $ingredients"
                            recipe.thumbnail = thumbnail

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