package com.sorabh.readerclub.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.sorabh.readerclub.repository.GetBookAccess
import com.sorabh.readerclub.retrofit.BookDescription
import org.json.JSONObject

class DescriptionViewModel(context: Context):ViewModel() {

   private val getBookAccess = GetBookAccess(context)

    suspend fun getBook(header:HashMap<String,String>, jsonObject: JsonObject):JsonObject?{
        return getBookAccess.getBook(header,jsonObject)
    }
}