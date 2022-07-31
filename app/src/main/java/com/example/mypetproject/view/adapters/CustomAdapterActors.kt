package com.example.mypetproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.actors.Cast
import com.example.mypetproject.view.MoviesDetailsActivity
import com.squareup.picasso.Picasso

class CustomAdapterActors(
    private val mListActors: List<Cast?>,
    val mItemClickListenerActors: MoviesDetailsActivity,
) : RecyclerView.Adapter<CustomAdapterActors.ViewHolder>() {

    interface ItemClickListenerActors {
        fun onItemClickActors(position: Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_actors, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mListActors[position]?.profile_path)
            .into(holder.imageView)


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mListActors.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewActors)

        init {
            ItemView.setOnClickListener {
                mListActors[position]?.id?.let { it -> mItemClickListenerActors.onItemClickActors(it) }
            }
        }
    }
}