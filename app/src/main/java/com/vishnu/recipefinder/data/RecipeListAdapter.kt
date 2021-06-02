package com.vishnu.recipefinder.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.vishnu.recipefinder.R
import com.vishnu.recipefinder.model.Recipe

class RecipeListAdapter(private val list: ArrayList<Recipe>, private val context: Context):
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //Widget Initializations
        var thumbnail = itemView.findViewById<ImageView>(R.id.recipeThumbnail)
        var title = itemView.findViewById<TextView>(R.id.recipeTitle)
        var ingredients = itemView.findViewById<TextView>(R.id.recipeIngredients)
        var linkButton = itemView.findViewById<Button>(R.id.recipeLinkButton)

        fun bindView(recipe: Recipe) {

        }
    }
}