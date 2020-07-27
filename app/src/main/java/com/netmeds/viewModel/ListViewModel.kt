package com.netmeds.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.netmeds.model.ListModel
import com.netmeds.repository.database.CartRepository
import com.netmeds.repository.retrofit.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListViewModel(mApplication: Application) : AndroidViewModel(mApplication) {

    private var testRepository: TestRepository = TestRepository
    private var cartRepository = CartRepository(mApplication)

    private val responseLiveData: LiveData<List<ListModel>?>? = testRepository.getResponseLiveData()

    init {
        Log.e("Rishabh", "init-")
        getTestFromRepo()
    }

    private fun getTestFromRepo() {
        testRepository.getTest()
    }

    fun getTestResponseLiveData(): LiveData<List<ListModel>?>? {
        return responseLiveData
    }

    fun addToCart(mModel: ListModel){
        cartRepository.insert(mModel)
    }
}