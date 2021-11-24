package com.sorabh.readerclub.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonObject
import com.sorabh.readerclub.R
import com.sorabh.readerclub.activities.MainActivity
import com.sorabh.readerclub.databinding.FragmentDescriptionBinding
import com.sorabh.readerclub.repository.GetBookAccess
import com.sorabh.readerclub.retrofit.BookDescription
import com.sorabh.readerclub.viewmodels.DescriptionViewModel
import com.sorabh.readerclub.viewmodels.DescriptionViewModelFactory
import kotlinx.coroutines.*


class DescriptionFragment(private val args: BookDescription? = null) : Fragment(){

    private lateinit var binding: FragmentDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_description, container, false)
        (activity as MainActivity).toolbar.title = "Book Description"


        if (args != null) {
            binding.bookDescription = args
            binding.bookDescription = args
            binding.descriptionFragment = this@DescriptionFragment

        } else {
            val argument = this.arguments?.getString("book_Id").toString()

            val jsonBook = JsonObject()
            jsonBook.addProperty("book_id", argument)

            val header = HashMap<String, String>()
            header["Content-type"] = "application/json"
            header["token"] = "025c40375fadfd"

            val job = SupervisorJob()
            val coroutineScope = CoroutineScope(job + Dispatchers.IO)

            coroutineScope.launch {
                val descriptionViewModel = ViewModelProvider(
                    this@DescriptionFragment,
                    DescriptionViewModelFactory(activity as Context)
                ).get(DescriptionViewModel::class.java)
                val jsonObject =
                    descriptionViewModel.getBook(header, jsonBook)
                val bookData = jsonObject?.getAsJsonObject("book_data")
                var bookDescription: BookDescription? = null
                if (bookData != null) {
                    val author: String = bookData.get("author").asString
                    val bookId: String = bookData.get("book_id").asString
                    val description: String = bookData.get("description").asString
                    val id: String = bookData.get("id").asString
                    val image: String = bookData.get("image").asString
                    val name: String = bookData.get("name").asString
                    val price: String = bookData.get("price").asString
                    val rating: String = bookData.get("rating").asString
                    bookDescription =
                        BookDescription(author, bookId, description, id, image, name, price, rating)
                }

                binding.bookDescription = bookDescription
                binding.descriptionFragment = this@DescriptionFragment


            }
        }

        binding.executePendingBindings()
        return binding.root
    }

    fun addToFavorite(bookDescription: BookDescription) {

        val job = SupervisorJob()
        val scope = CoroutineScope(job + Dispatchers.IO)
        scope.launch {
            val getBookAccess = GetBookAccess(activity as Context)
            getBookAccess.insertBook(bookDescription)
        }
        Toast.makeText(activity as Context, "This book added to your Favorite", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}