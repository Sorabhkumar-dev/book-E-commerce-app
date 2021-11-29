package com.sorabh.readerclub.database

import androidx.room.*
import com.sorabh.readerclub.retrofit.BookDescription

@Dao
interface BookDao {
    @Insert
    suspend fun insertBook(bookDescription: BookDescription)

    @Delete
    suspend fun deleteBook(bookDescription: BookDescription)

    @Update
    suspend fun  updateBook(bookDescription: BookDescription)

    @Query("SELECT * FROM BookEntry")
    suspend fun getFavoriteBooks():List<BookDescription>

    @Query("SELECT * FROM BookEntry WHERE book_id = :bookId")
    suspend fun getFavoriteBook(bookId:String):BookDescription
}