package com.trade.itunes_api_manohar.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trade.itunes_api_manohar.models.ResultModel

@Database (entities = [(ResultModel::class)],version = 1)
@TypeConverters(DataTypeConverters::class)
abstract class AppDB : RoomDatabase() {
    abstract fun searchDao(): TracksDAO
}
