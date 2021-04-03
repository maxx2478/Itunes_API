package com.trade.itunes_api_manohar.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.trade.itunes_api_manohar.models.ResultModel
import java.lang.reflect.Type
import java.util.*

class DataTypeConverters {

    var gson = Gson()

    @TypeConverter
    fun toList(strings: String): List<String> {
        val list = mutableListOf<String>()
        val array = strings.split(",")
        for (s in array) {
            list.add(s)
        }
        return list
    }

    @TypeConverter
    fun toString(strings: List<String>): String {
        var result = ""
        strings.forEachIndexed { index, element ->
            result += element
            if(index != (strings.size-1)){
                result += ","
            }
        }
        return result
    }


    @TypeConverter
    fun fromString(value: String?): List<ResultModel> {
        val mapType = object : TypeToken<List<ResultModel?>?>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(list: List<ResultModel?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }




}