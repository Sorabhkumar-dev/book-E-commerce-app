package com.sorabh.readerclub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sorabh.readerclub.R
import com.sorabh.readerclub.databinding.BookCardBinding
import com.sorabh.readerclub.retrofit.Data

class RecycleViewAdapter internal constructor(
     var bookList: List<Data>,
     var bookListener: RecycleViewViewHolder.OnBookItemClicked
) : RecyclerView.Adapter<RecycleViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewViewHolder =
        RecycleViewViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecycleViewViewHolder, position: Int) =
        holder.bind(bookList[position], bookListener)

    override fun getItemCount(): Int = bookList.size

    fun updateBookList(newBookList: List<Data>) {
        bookList = newBookList
        notifyDataSetChanged()
    }
}

class RecycleViewViewHolder(private var binding: BookCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(currentBook: Data, listener: OnBookItemClicked) {
        binding.data = currentBook
        binding.onBookItemClicked = listener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): RecycleViewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: BookCardBinding = DataBindingUtil
                .inflate(
                    layoutInflater, R.layout.book_card,
                    parent, false
                )
            return RecycleViewViewHolder(binding)
        }
    }

    interface OnBookItemClicked {
        fun onBookClicked(data: Data)
    }
}