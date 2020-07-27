package com.netmeds.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.netmeds.model.ListModel


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartModel: ListModel)

    @Delete
    fun deleteRow(mModel: ListModel)

    @Query("SELECT * from cart_table ")
    fun getAll(): LiveData<MutableList<ListModel>>?

    @Query("SELECT SUM(minPrice) AS value from cart_table")
    fun getCartTotal(): LiveData<Int>

    @Query("DELETE FROM cart_table")
    fun deleteAll()
}