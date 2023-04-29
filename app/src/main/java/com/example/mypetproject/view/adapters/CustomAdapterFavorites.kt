package com.example.mypetproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.Details.MoviesDetails
import com.squareup.picasso.Picasso

class CustomAdapterFavorites(
) : RecyclerView.Adapter<CustomAdapterFavorites.ViewHolder>() {

    interface onClickItemFav {
        fun onClikedItemFavorite(id: Int)
    }

    // create new views
    var mList = listOf<MoviesDetails>()
    private var mItemClickListener: onClickItemFav? = null

    fun setOnMovieClickListener(listener: onClickItemFav) {
        mItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList[position]?.poster_path)
            .into(holder.imageView)

        holder.itemView.rootView.setOnClickListener {
            mItemClickListener?.onClikedItemFavorite(id = itemsViewModel.id)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)

    }
}