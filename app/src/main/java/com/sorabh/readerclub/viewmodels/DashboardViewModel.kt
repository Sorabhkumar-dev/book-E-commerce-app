package com.sorabh.readerclub.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sorabh.readerclub.repository.GetBookAccess
import com.sorabh.readerclub.retrofit.ResponseBook

class DashboardViewModel(context: Context) : ViewModel() {
    private val getBookAccess = GetBookAccess(context)
    suspend fun getBooks(header: HashMap<String, String>): ResponseBook? {
        return getBookAccess.getBooks(header)
    }
}