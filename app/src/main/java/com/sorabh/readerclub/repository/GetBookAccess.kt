package com.sorabh.readerclub.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.sorabh.readerclub.database.BookDatabase
import com.sorabh.readerclub.retrofit.BookAPI
import com.sorabh.readerclub.retrofit.BookDescription
import com.sorabh.readerclub.retrofit.GetBookAPI
import com.sorabh.readerclub.retrofit.ResponseBook


class GetBookAccess(context: Context) {

    /*--------------------For Network Call--------------------*/

    //for network call
    private val bookAPI = GetBookAPI.getInstance().create(BookAPI::class.java)

    suspend fun getBooks(header: HashMap<String, String>): ResponseBook? {
        val response = bookAPI.getBooks(header)
        return response.body()
    }

    suspend fun getBook(header: HashMap<String, String>, jsonObject: JsonObject): JsonObject? {
        val response = bookAPI.getBook(header, jsonObject)
        return response.body()
    }


    /* --------------------Room Database Access--------------------*/

    //for room database access
    private val bookDao = BookDatabase.getBookDatabaseInstance(context).getBookDao()

    suspend fun insertBook(bookDescription: BookDescription) {
        bookDao.insertBook(bookDescription)
    }

    suspend fun deleteBook(bookDescription: BookDescription) {
        bookDao.deleteBook(bookDescription)
    }

    suspend fun getFavoriteBooks():List<BookDescription> {
       return bookDao.getFavoriteBooks()
    }

    suspend fun getFavoriteBook(bookId:String):BookDescription{
        return bookDao.getFavoriteBook(bookId)
    }
}