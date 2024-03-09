package com.veko.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = true
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "weatherexampledatabase"

        fun create(context: Context): WeatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}