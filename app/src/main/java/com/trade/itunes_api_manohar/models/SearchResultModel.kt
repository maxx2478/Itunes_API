package com.trade.itunes_api_manohar.models

import com.google.gson.annotations.SerializedName

data class SearchResultModel(

    @SerializedName("resultCount")
    val resultcount:Int?,

    @SerializedName("results")
    val results:List<ResultModel>?=null


)

{

     fun getCount(): Int?
    {
        return resultcount
    }

    @JvmName("getResults1")
    fun getResults():List<ResultModel>?
    {
        return  results
    }

}
