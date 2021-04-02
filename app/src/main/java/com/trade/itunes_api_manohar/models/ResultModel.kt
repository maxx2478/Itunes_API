package com.trade.itunes_api_manohar.models

import com.google.gson.annotations.SerializedName

data class ResultModel(

@SerializedName("artistName")
var artistName:String?,

@SerializedName("trackName")
var trackName:String?,

@SerializedName("artworkUrl100")
var artworkUrl:String?


)
