package com.veko.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.veko.data.storage.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entity: WeatherEntity): Long

    @Query("SELECT*FROM CITY_TABLE")
    fun getWeatherByCoords(): Flow<List<WeatherEntity>>
}