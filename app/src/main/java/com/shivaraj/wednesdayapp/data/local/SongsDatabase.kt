package com.shivaraj.wednesdayapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SongsModel::class], version = 1, exportSchema = false)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun songsDao(): SongsDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SongsDatabase? = null

        fun getDatabase(context: Context): SongsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongsDatabase::class.java,
                    "songs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
