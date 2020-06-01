package com.shivaraj.wednesdayapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.shivaraj.wednesdayapp.R
import com.shivaraj.wednesdayapp.ViewModelFactory
import com.shivaraj.wednesdayapp.data.local.SongsDAO
import com.shivaraj.wednesdayapp.data.local.SongsDatabase
import com.shivaraj.wednesdayapp.data.local.SongsModel
import com.shivaraj.wednesdayapp.data.remote.Status
import com.shivaraj.wednesdayapp.repo.Repository
import com.shivaraj.wednesdayapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewMode: MainViewModel
    lateinit var dataRepository: Repository
    lateinit var db: SongsDAO
    lateinit var adapter: SongsAdapter
    val songsList = ArrayList<SongsModel>()
    var searchString = "justin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRepository = Repository(application)

        val viewModeFact = ViewModelFactory(dataRepository)
        db = SongsDatabase.getDatabase(this).songsDao()
        viewMode = ViewModelProviders.of(this, viewModeFact).get(MainViewModel::class.java)

        initView()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(queryString: String?): Boolean {

                queryString?.let {
                    searchString = it
                    loadData(it)
                }

                return true

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        loadData(searchString)

        //subscribe()
    }

    private fun initView() {
        adapter = SongsAdapter(songsList)
        val layout = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter

    }

    private fun subscribe() {

        db.getAll().observe(this, Observer {


        })
    }

    private fun loadData(queryString: String) {

        viewMode.getMatchingSongs(queryString).observe(this, Observer {
            adapter.clear()
            adapter.addTasks(it)
            adapter.notifyDataSetChanged()

        })
    }

}
