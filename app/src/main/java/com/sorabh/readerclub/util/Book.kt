package com.sorabh.readerclub.util

data class Book(
    val id: String,
    val bookId: String,
    val bookName: String,
    val bookAuthor:String="",
    val bookRating: String,
    val bookPrice: String,
    val bookImage: String,
    val bookDescription: String=""
)
