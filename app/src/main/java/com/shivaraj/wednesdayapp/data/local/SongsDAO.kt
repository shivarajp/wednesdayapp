package com.shivaraj.wednesdayapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SongsDAO {

    @Query("SELECT * FROM  songs_table WHERE trackId = :id")
    public fun get(id: Int): LiveData<SongsModel>

    @Query("SELECT * FROM  songs_table WHERE artistName LIKE :quesry")
    public fun search(quesry: String): List<SongsModel>

    @Query("SELECT * FROM  songs_table")
    fun getAll(): LiveData<List<SongsModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg songs: SongsModel): LongArray

    @Delete
    fun delete(jobModel: SongsModel)

    @Query("DELETE FROM songs_table")
    fun deleteAll()
}