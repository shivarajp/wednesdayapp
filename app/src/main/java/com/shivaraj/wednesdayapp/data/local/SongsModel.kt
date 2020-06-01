package com.shivaraj.wednesdayapp.data.local

import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide


@Entity(tableName = "songs_table")
class SongsModel (
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "trackId")
    var trackId: Int,
    @field:ColumnInfo(name = "artistName")
    val artistName: String,
    @field:ColumnInfo(name = "trackName")
    val trackName: String,
    @field:ColumnInfo(name = "artworkUrl30")
    val artworkUrl30: String,
    @field:ColumnInfo(name = "trackPrice")
    val trackPrice: Double,
    @field:ColumnInfo(name = "releaseDate")
    val releaseDate: String

): Serializable{


    companion object{


    }


}

