package com.sorabh.readerclub.retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface BookAPI {
//    var header: HashMap<String, String>()
//    header ["Content-type"] = "application/json"
//    header ["token"] = "025c40375fadfd"

    @GET("fetch_books/")
    suspend fun getBooks(@HeaderMap header: HashMap<String, String>): Response<ResponseBook>

    @POST("get_book/")
    suspend fun getBook(
        @HeaderMap header: HashMap<String, String>,
        @Body jsonObject: JsonObject
    ): Response<JsonObject>
}
