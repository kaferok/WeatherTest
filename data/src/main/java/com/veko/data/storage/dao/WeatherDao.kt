package com.veko.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.veko.data.storage.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertOrUpdate(entity: CityEntity): Int

    @Query("SELECT*FROM CITY_TABLE")
    fun getWeatherByCoords(): Flow<List<CityEntity>>
}