package com.trade.itunes_api_manohar.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ResultModel(

@SerializedName("artistName")
@PrimaryKey
var artistName:String="",

@SerializedName("trackName")
@ColumnInfo(name = "trackName")
var trackName:String="",

@SerializedName("artworkUrl100")
@ColumnInfo(name = "artworkUrl100")
var artworkUrl:String=""


)
