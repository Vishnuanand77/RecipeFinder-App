package com.vishnu.recipefinder.data

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vishnu.recipefinder.R
import com.vishnu.recipefinder.model.Recipe

class RecipeListAdapter(private val list: ArrayList<Recipe>, private val context: Context):
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //Widget Initializations
        var thumbnail: ImageView = itemView.findViewById<ImageView>(R.id.recipeThumbnail)
        var title: TextView = itemView.findViewById<TextView>(R.id.recipeTitle)
        var ingredients: TextView = itemView.findViewById<TextView>(R.id.recipeIngredients)
        var linkButton: Button = itemView.findViewById<Button>(R.id.recipeLinkButton)

        fun bindView(recipe: Recipe) {
            if (!TextUtils.isEmpty(recipe.thumbnail)) {
                //Loading thumbnail from remote API into image view on the card.
                Picasso.get()
                    .load(recipe.thumbnail)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(thumbnail)
            } else {
                //Load a default image if the API does not have an associated thumbnail
                Picasso.get().load(R.mipmap.ic_launcher).into(thumbnail)
            }
            //TextView Assignments
            title.text = recipe.title
            ingredients.text = recipe.ingredients

            //Link Button onClick
            linkButton.setOnClickListener {
                TODO("IMPLEMENT LINK OPEN WITH BROWSER FUNCTIONS")
            }
        }
    }
}