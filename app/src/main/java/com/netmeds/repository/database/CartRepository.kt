package com.netmeds.repository.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.netmeds.model.ListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CartRepository(application: Application) {
    private var mCartDao: CartDao? = null
    private var mAllData: LiveData<MutableList<ListModel>>? = null
    private var mCartCost: LiveData<Int>? = null
    var mDeleteAll: MutableLiveData<Boolean>? = MutableLiveData()

    init {
        val db: CartRoomDatabase? = CartRoomDatabase.getDatabase(application)
        mCartDao = db?.wordDao()
        mAllData = mCartDao?.getAll()
        mCartCost = mCartDao?.getCartTotal()
    }

    fun getAllData(): LiveData<MutableList<ListModel>>? {
        return mAllData
    }

    fun getCartCost(): LiveData<Int>? {
        return mCartCost
    }

    fun insert(model: ListModel) {
        GlobalScope.launch(Dispatchers.IO) {
            mCartDao?.insert(model)
        }
    }

    fun deleteRow(model: ListModel) {
        GlobalScope.launch(Dispatchers.IO) {
            mCartDao?.deleteRow(model)
        }
    }

    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            mCartDao?.deleteAll()
            mDeleteAll?.postValue(true)
        }
    }
}