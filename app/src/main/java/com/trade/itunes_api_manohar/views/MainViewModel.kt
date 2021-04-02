package com.trade.itunes_api_manohar.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trade.itunes_api_manohar.models.SearchResultModel
import com.trade.itunes_api_manohar.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var apiService = ApiService ()
    var resultList = MutableLiveData<SearchResultModel>()
    val loading = MutableLiveData<Boolean>()



    fun getData(query:String)
    {

        loading.value = true
        val call: Call<SearchResultModel>? = apiService!!.getResults(query)
        call!!.enqueue(object : Callback<SearchResultModel?>
        {
            override fun onResponse(
                call: Call<SearchResultModel?>,
                response: Response<SearchResultModel?>
            ) {

                if (response.isSuccessful)
                {

                    resultList.value = response.body()
                    loading.value = false
                }
            }

            override fun onFailure(call: Call<SearchResultModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }




}