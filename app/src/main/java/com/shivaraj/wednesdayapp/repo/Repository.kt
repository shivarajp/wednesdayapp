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
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

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

       }catch (e: Exception){

           val list = getSearchedSongsFromDb(queryString)
           responseHandler.handleSuccess(list as ArrayList)
       }
    }

    private fun getSearchedSongsFromDb(queryString: String): List<SongsModel> {

        return db.search("%$queryString%")
    }


    private fun storeInRoom(responseJsonData: SearchResultResponseModel) {

        //val list = arrayListOf<SongsModel>()
        responseJsonData.results.forEach {

            val eachTrack = SongsModel(it.trackId,
                it.artistName, trackName = it.trackName, trackPrice = it.trackPrice,
                artworkUrl30 = it.artworkUrl30, releaseDate = it.releaseDate)
            //list.add(eachTrack)
            db.insertAll(eachTrack)
        }
    }


    private fun getFile(data: ResponseBody): String {

        if (data==null)
            return ""
        var input: InputStream? = null
        try {
            input = data.byteStream()

            val sb = StringBuilder()
            var line: String?

            val br = BufferedReader(InputStreamReader(input))
            line = br.readLine()

            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
            br.close()

            return sb.toString()

        }catch (e:Exception){
            //Log.e("saveFile",e.toString())
        }
        finally {
            input?.close()
        }
        return ""
    }


}