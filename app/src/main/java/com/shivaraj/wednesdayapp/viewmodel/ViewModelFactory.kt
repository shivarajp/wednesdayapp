package com.shivaraj.wednesdayapp

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivaraj.wednesdayapp.repo.Repository
import com.shivaraj.wednesdayapp.viewmodel.MainViewModel


class ViewModelFactory(private val mDataSource: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            return MainViewModel(mDataSource) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}