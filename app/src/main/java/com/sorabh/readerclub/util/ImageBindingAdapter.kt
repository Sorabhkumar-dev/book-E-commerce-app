package com.sorabh.readerclub.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.sorabh.readerclub.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageSrcUrl")
fun ImageView.imageSrcUrl(url: String?) {
    if (url != null) {
        Picasso.with(this.context)
            .load(url).error(R.drawable.blue_book)
            .into(this)
    }
}
@BindingAdapter("bookRating")
fun RatingBar.bookRating(rating: String?){
    if (rating != null) {
        this.rating = rating.toFloat()
    }
}