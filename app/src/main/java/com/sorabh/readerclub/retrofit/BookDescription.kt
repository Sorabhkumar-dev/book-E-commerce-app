package com.sorabh.readerclub.retrofit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BookEntry")
data class BookDescription(
    @ColumnInfo(name = "Book_Author")
    val author: String,
    @PrimaryKey
    @ColumnInfo(name="Book_Id")
    val book_id: String,
    @ColumnInfo(name = "Book_Description")
    val description: String,
    @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name= "Book_Image")
    val image: String,
    @ColumnInfo(name= "Book_Name")
    val name: String,
    @ColumnInfo(name = "Book_Price")
    val price: String,
    @ColumnInfo(name= "Book_Rating")
    val rating: String
)