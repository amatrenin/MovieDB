package com.example.mypetproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.R
import com.example.mypetproject.data.review.ResultReview
import com.example.mypetproject.view.MoviesDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CustomAdapterReview(
    private val mListReview: List<ResultReview?>,
    private val mItemClickListenerReview: MoviesDetailsActivity
) : RecyclerView.Adapter<CustomAdapterReview.ViewHolder>() {

    interface ItemClickListenerReview {
        fun onItemClickReview(position: Int)
    }

    private lateinit var mTitleReview: TextView

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_review, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mListReview[position]

        // sets the text to the textview from our itemHolder class
        holder.textView2.text = ItemsViewModel?.author
        holder.textView1.text = ItemsViewModel?.content

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mListReview[position]?.url)
            .into(holder.imageView)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mListReview.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //   val imageView: ImageView = itemView.findViewById(R.id.imAvatarReview)
        val textView1: TextView = itemView.findViewById(R.id.tvTextReview)
        val textView2: TextView = itemView.findViewById(R.id.tvTitleReview)
        val imageView: ImageView = itemView.findViewById(R.id.imReview)
    }
}