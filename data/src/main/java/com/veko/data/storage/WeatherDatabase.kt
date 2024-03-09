package com.veko.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.veko.data.storage.dao.WeatherDao
import com.veko.data.storage.entity.CityEntity
import com.veko.data.storage.entity.CommonWeatherEntity
import com.veko.data.storage.entity.LatestWeatherEntity

@Database(
    entities = [
        CityEntity::class,
        LatestWeatherEntity::class,
        CommonWeatherEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "weatherexampledatabase"

        fun getInstance(context: Context): WeatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun weatherDao(): WeatherDao
}