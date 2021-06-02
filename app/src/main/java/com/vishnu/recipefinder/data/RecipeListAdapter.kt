package com.vishnu.recipefinder.data

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vishnu.recipefinder.R
import com.vishnu.recipefinder.activities.ShowLinkActivity
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
        private var thumbnail: ImageView = itemView.findViewById(R.id.recipeThumbnail)
        private var title: TextView = itemView.findViewById(R.id.recipeTitle)
        private var ingredients: TextView = itemView.findViewById(R.id.recipeIngredients)
        private var cardView: CardView = itemView.findViewById(R.id.cardViewId)

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
//            linkButton.setOnClickListener {
//                val intent = Intent(context, ShowLinkActivity::class.java)
//                intent.putExtra("link", recipe.link.toString()) //Forwarding the recipe url
//
//                //Starting a WebView
//                context.startActivity(intent)
//            }

            //Card View onClick
            cardView.setOnClickListener {
                val intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.link.toString()) //Forwarding the recipe url

                //Starting a WebView
                context.startActivity(intent)

//                Toast.makeText(context, "CardView click works!", Toast.LENGTH_SHORT).show()
            }
            
        }
    }
}