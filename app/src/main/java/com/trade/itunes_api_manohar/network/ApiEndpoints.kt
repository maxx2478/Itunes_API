package com.trade.itunes_api_manohar.network

import com.trade.itunes_api_manohar.models.SearchResultModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoints
{


    @POST("search?")
    fun getSearchResults(
        @Query("term") searchTerm: CharSequence?,
    ): Call<SearchResultModel>?

}