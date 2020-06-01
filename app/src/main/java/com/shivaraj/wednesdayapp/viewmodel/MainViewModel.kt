package com.shivaraj.wednesdayapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shivaraj.wednesdayapp.data.local.SongsModel
import com.shivaraj.wednesdayapp.data.models.SearchResultResponseModel
import com.shivaraj.wednesdayapp.data.remote.Resource
import com.shivaraj.wednesdayapp.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(val dataRepository: Repository): ViewModel() {

    fun getMatchingSongs(queryString: String): LiveData<ArrayList<SongsModel>> {

        return liveData(Dispatchers.IO) {
            //emit(Resource.loading(null))
            val data = dataRepository.getMatchingSongs(queryString)
            emit(data.data!!)
        }
    }

}