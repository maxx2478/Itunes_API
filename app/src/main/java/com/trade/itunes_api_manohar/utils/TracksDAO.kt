package com.trade.itunes_api_manohar.utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trade.itunes_api_manohar.models.ResultModel


@Dao
interface TracksDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResults(list: List<ResultModel>)

    @Query(value = "Select * from ResultModel")
    fun getAllResults() : List<ResultModel>
}