package com.sorabh.readerclub.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sorabh.readerclub.R
import com.sorabh.readerclub.activities.MainActivity
import com.sorabh.readerclub.adapter.RecycleViewAdapter
import com.sorabh.readerclub.adapter.RecycleViewViewHolder
import com.sorabh.readerclub.databinding.FragmentDashboardBinding
import com.sorabh.readerclub.retrofit.Data
import com.sorabh.readerclub.retrofit.ResponseBook
import com.sorabh.readerclub.viewmodels.DashboardViewModel
import kotlinx.coroutines.*

class DashboardFragment : Fragment(), RecycleViewViewHolder.OnBookItemClicked {
    private lateinit var _binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        (activity as MainActivity).toolbar.title = "Book List"

        val jobScope = SupervisorJob()
        val list = ArrayList<Data>()
        val coroutineScope = CoroutineScope(jobScope + Dispatchers.IO)

        coroutineScope.launch {

            val header = HashMap<String, String>()
            header["Content-type"] = "application/json"
            header["token"] = "025c40375fadfd"
            val dashboardViewModel = DashboardViewModel(activity as Context)
            var str: ResponseBook? = null
            try {
                str = dashboardViewModel.getBooks(header)

                val recycleViewAdapter = RecycleViewAdapter(list, this@DashboardFragment)
                withContext(Dispatchers.Main) {
                    recycleViewAdapter.updateBookList(str!!.data)
                    _binding.progressBar.visibility = ProgressBar.GONE


                }

                with(_binding.recycleView) {
                    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                    adapter = recycleViewAdapter
                }
            } catch (e: java.net.ConnectException) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        activity as Context,
                        "Could Not Connect To Internet!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } finally {

            }
        }
        return _binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()
    }

    override fun onBookClicked(data: Data) {
        val args = Bundle()
        args.putString("book_Id", data.book_id)
        val frag = DescriptionFragment()
        frag.arguments = args
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, frag)
            .addToBackStack("DashboardFragment")
            .commit()
    }
}