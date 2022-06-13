//package com.example.areader.api
//
//import com.example.areader.model.ApiModels.BookModel
//import retrofit2.http.GET
//import retrofit2.http.Query
//import javax.inject.Singleton
//
//@Singleton
//interface BooksApi {
//    @GET("Volumes")
//    suspend fun booksApi(@Query("q") query: String = "") = BookModel::class.java
//
//}