package com.shivaraj.wednesdayapp.data.remote

import com.shivaraj.wednesdayapp.data.models.SearchResultResponseModel
import okhttp3.ResponseBody
import retrofit2.http.*


interface AppleAPIService {

    @Headers("Accept: application/json")
    @GET("search")
    suspend fun getMatchingSongs(
        @Header("Content-Type") contentType: String,
        @Query("term") queryString : String
    ): SearchResultResponseModel

}