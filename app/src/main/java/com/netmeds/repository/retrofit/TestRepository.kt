package com.netmeds.repository.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.netmeds.model.ListModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TestRepository {
    private val baseUrl = "https://5f1a8228610bde0016fd2a74.mockapi.io/"
    private var testSearchService: TestSearchService
    private var responseLiveData: MutableLiveData<List<ListModel>> = MutableLiveData()

    init {
        val client = OkHttpClient.Builder().build()
        testSearchService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestSearchService::class.java)
    }

    fun getTest() {
        testSearchService.getTest()
            .enqueue(object : Callback<List<ListModel>?> {
                override fun onResponse(
                    call: Call<List<ListModel>?>?,
                    response: Response<List<ListModel>?>
                ) {
                    if (response.body() != null) {
                        responseLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(
                    call: Call<List<ListModel>?>?,
                    t: Throwable?
                ) {
                    responseLiveData.postValue(null)
                }
            })
    }

    fun getResponseLiveData(): LiveData<List<ListModel>?>? {
        return responseLiveData
    }
}