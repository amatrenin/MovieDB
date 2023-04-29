package com.example.mypetproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.Details.MoviesDetails
import com.squareup.picasso.Picasso

class CustomAdapter(
) : PagingDataAdapter<MoviesDetails, CustomAdapter.ViewHolder>(
    MovieComparator
) {

    interface ItemClickListener {
        fun onItemClick(id: Int)
    }

    private var mItemClickListener: ItemClickListener? = null

    fun setOnMovieClickListener(listener: ItemClickListener) {
        mItemClickListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)!!
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
            .into(holder.imageView)
        holder.itemView.rootView.setOnClickListener {
            mItemClickListener?.onItemClick(id = movie.id)
        }
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }
}

object MovieComparator : DiffUtil.ItemCallback<MoviesDetails>() {
    override fun areItemsTheSame(
        oldItem: MoviesDetails,
        newItem: MoviesDetails
    ): Boolean {
        // Id is unique.
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: MoviesDetails,
        newItem: MoviesDetails
    ): Boolean {
        return oldItem == newItem
    }
}


