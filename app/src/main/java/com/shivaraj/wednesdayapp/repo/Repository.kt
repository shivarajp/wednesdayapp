package com.shivaraj.wednesdayapp.repo

import android.app.Application
import com.google.gson.Gson
import com.shivaraj.wednesdayapp.data.local.SongsDatabase
import com.shivaraj.wednesdayapp.data.local.SongsModel
import com.shivaraj.wednesdayapp.data.models.SearchResultResponseModel
import com.shivaraj.wednesdayapp.data.remote.AppleAPIService
import com.shivaraj.wednesdayapp.data.remote.Resource
import com.shivaraj.wednesdayapp.data.remote.ResponseHandler
import com.shivaraj.wednesdayapp.data.remote.RetrofitGenerator

open class Repository(val app: Application) {

    private val api: AppleAPIService = RetrofitGenerator.createService(
        AppleAPIService::class.java
    )
    private val responseHandler: ResponseHandler = ResponseHandler()
    private val db = SongsDatabase.getDatabase(app).songsDao()

    private val CONTENT_TYPE = "application/json"

    suspend fun getMatchingSongs(queryString: String): Resource<ArrayList<SongsModel>> {

        return try {
            val response = api.getMatchingSongs(CONTENT_TYPE, queryString)
            storeInRoom(response)

            val list = getSearchedSongsFromDb(queryString)
            responseHandler.handleSuccess(list as ArrayList)

        } catch (e: Exception) {

            val list = getSearchedSongsFromDb(queryString)
            responseHandler.handleSuccess(list as ArrayList)
        }
    }

    private fun getSearchedSongsFromDb(queryString: String): List<SongsModel> {

        return db.search("%$queryString%")
    }


    private fun storeInRoom(responseJsonData: SearchResultResponseModel) {

        responseJsonData.results.forEach {

            val eachTrack = SongsModel(
                it.trackId,
                it.artistName, trackName = it.trackName, trackPrice = it.trackPrice,
                artworkUrl30 = it.artworkUrl30, releaseDate = it.releaseDate
            )
            db.insertAll(eachTrack)
        }
    }

}