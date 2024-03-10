package com.veko.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.veko.data.storage.converter.DailyWeatherConverter
import com.veko.data.storage.dao.WeatherDao
import com.veko.data.storage.entity.WeatherEntity
import com.veko.data.storage.entity.CommonWeatherEntity
import com.veko.data.storage.entity.CurrentWeatherEntity
import com.veko.data.storage.entity.DailyWeatherEntity
import com.veko.data.storage.entity.TemperatureEntity

@Database(
    entities = [
        WeatherEntity::class,
        CurrentWeatherEntity::class,
        CommonWeatherEntity::class,
        DailyWeatherEntity::class,
        TemperatureEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DailyWeatherConverter::class)
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