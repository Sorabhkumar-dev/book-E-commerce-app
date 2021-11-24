package com.sorabh.readerclub.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sorabh.readerclub.retrofit.BookDescription

class BookDescrptionDiffutil(
    private val oldList: List<BookDescription>,
    private val newList: List<BookDescription>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].book_id == newList[newItemPosition].book_id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (author1, _, description1, id1, image1, name1, price1, rating1) = oldList[oldItemPosition]
        val (author, _, description, id, image, name, price, rating) = newList[newItemPosition]
        return name == name1 && author == author1 && description == description1 && id == id1 && image1 == image && price1 == price && rating == rating1
    }
}
