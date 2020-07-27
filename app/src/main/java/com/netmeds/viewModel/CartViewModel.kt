package com.netmeds.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.netmeds.model.ListModel
import com.netmeds.repository.database.CartRepository

class CartViewModel(mApplication: Application) : AndroidViewModel(mApplication) {

    private var cartRepository = CartRepository(mApplication)
    val cartLiveData: LiveData<MutableList<ListModel>>? = cartRepository.getAllData()
    val cartCostLiveData: LiveData<Int>? = cartRepository.getCartCost()
    val mPurchaseLiveData: MutableLiveData<Boolean>? = cartRepository.mDeleteAll

    fun deleteFromDb(model: ListModel) {
        cartRepository.deleteRow(model)
    }

    fun purchase(){
        cartRepository.deleteAll()
    }
}