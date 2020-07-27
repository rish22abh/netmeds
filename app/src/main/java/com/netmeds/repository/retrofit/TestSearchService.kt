package com.netmeds.repository.retrofit

import com.netmeds.model.ListModel
import retrofit2.Call
import retrofit2.http.GET

interface TestSearchService {
    @GET("getTestList")
    fun getTest(): Call<List<ListModel>>
}