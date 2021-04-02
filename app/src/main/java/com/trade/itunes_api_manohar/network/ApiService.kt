package com.trade.itunes_api_manohar.network

import com.trade.itunes_api_manohar.models.SearchResultModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService
{

    private  val BASE_URL="https://itunes.apple.com/"
    private val api:ApiEndpoints

    init {

        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiEndpoints::class.java)

    }

    fun getResults(query :String): Call<SearchResultModel>?
    {
        return api.getSearchResults(query)
    }



}