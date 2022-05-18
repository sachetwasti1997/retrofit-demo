package com.example.retrofitdemo.network

import com.example.retrofitdemo.data.AlbumList
import retrofit2.Response
import retrofit2.http.GET

interface AlbumNetwork {

    @GET("albums")
    suspend fun getAlbums():Response<AlbumList>

}