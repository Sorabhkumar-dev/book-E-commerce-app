package com.sorabh.readerclub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sorabh.readerclub.R
import com.sorabh.readerclub.databinding.BookDescrptionAdapterLayoutBinding
import com.sorabh.readerclub.retrofit.BookDescription

class FavoriteAdapter constructor(
    private var onBookDescriptionClicked: FavoriteBookViewHolder.OnBookDescriptionClicked
) : RecyclerView.Adapter<FavoriteBookViewHolder>() {

    private var bookDescriptionList = listOf<BookDescription>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBookViewHolder =
        FavoriteBookViewHolder.bindingInflater(parent)

    override fun onBindViewHolder(holder: FavoriteBookViewHolder, position: Int) =
        holder.bind( bookDescriptionList[position], onBookDescriptionClicked)

    override fun getItemCount(): Int = bookDescriptionList.size

    fun updateBookDescriptionList( newList:List<BookDescription>) {
        val diffCallback = BookDescrptionDiffutil(this.bookDescriptionList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.bookDescriptionList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

class FavoriteBookViewHolder(private var binding: BookDescrptionAdapterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        currentBookDescription: BookDescription,
        listener: OnBookDescriptionClicked
    ) {
        binding.bookSDescription = currentBookDescription
        binding.onBookDescriptionClicked = listener
        binding.executePendingBindings()
    }

    companion object {
        fun bindingInflater(parent: ViewGroup): FavoriteBookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<BookDescrptionAdapterLayoutBinding>(
                layoutInflater,
                R.layout.book_descrption_adapter_layout,
                parent,
                false
            )
            return FavoriteBookViewHolder(binding)
        }
    }

    interface OnBookDescriptionClicked {
        fun onBookDescriptionClicked(bookDescription: BookDescription)

        fun deleteBookDescription(bookDescription: BookDescription)
    }
}

