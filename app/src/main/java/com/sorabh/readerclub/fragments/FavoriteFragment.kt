package com.sorabh.readerclub.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sorabh.readerclub.R
import com.sorabh.readerclub.activities.MainActivity
import com.sorabh.readerclub.adapter.FavoriteAdapter
import com.sorabh.readerclub.databinding.FragmentFavoriteBinding
import com.sorabh.readerclub.repository.GetBookAccess
import com.sorabh.readerclub.retrofit.BookDescription
import kotlinx.coroutines.*


/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment(),
    com.sorabh.readerclub.adapter.FavoriteBookViewHolder.OnBookDescriptionClicked {
    private lateinit var _binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        (activity as MainActivity).toolbar.title = "Favorite Books"

        val job = SupervisorJob()
        val scope = CoroutineScope(job + Dispatchers.IO)
        scope.launch {
            val getBookAccess = GetBookAccess(activity as Context)
            val bookList = getBookAccess.getFavoriteBooks()
            val favoriteAdapter = FavoriteAdapter(this@FavoriteFragment)

            withContext(Dispatchers.Main) {
                favoriteAdapter.updateBookDescriptionList(bookList)
                with(_binding.recycleView) {
                    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                    this.adapter = favoriteAdapter
                    layoutManager = GridLayoutManager(activity as Context, 2)
                }
            }
        }
        _binding.executePendingBindings()
        return _binding.root
    }

    override fun deleteBookDescription(bookDescription: BookDescription) {
        val job = SupervisorJob()
        val scope = CoroutineScope(job + Dispatchers.IO)
        Toast.makeText(
            activity as Context,
            "This Book deleted From Your Favorite Books",
            Toast.LENGTH_LONG
        ).show()
        scope.launch {
            val getBookAccess = GetBookAccess(activity as Context)
            getBookAccess.deleteBook(bookDescription)
            withContext(Dispatchers.Main) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, FavoriteFragment()).commit()
            }
        }
    }


    override fun onBookDescriptionClicked(bookDescription: BookDescription) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout,DescriptionFragment(bookDescription))
            .addToBackStack("Favorite_Fragment")
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()
    }
}