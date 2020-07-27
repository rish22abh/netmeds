package com.netmeds.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_table")
data class ListModel(
    @ColumnInfo(name = "s_no")
    @SerializedName("S.no")
    var s_no: Int,

    @PrimaryKey
    @ColumnInfo(name = "itemId") var itemId: String,
    @ColumnInfo(name = "itemName") var itemName: String? = null,
    @ColumnInfo(name = "type") var type: String? = null,
    @ColumnInfo(name = "Keyword") var Keyword: String? = null,

    @ColumnInfo(name = "best_sellers")
    @SerializedName("Best-sellers")
    var best_sellers: String? = null,

    @ColumnInfo(name = "testCount") var testCount: String? = null,

    @ColumnInfo(name = "included_tests")
    @SerializedName("Included Tests")
    var included_tests: String? = null,

    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "minPrice") var minPrice: Int,
    @ColumnInfo(name = "labName") var labName: String? = null,
    @ColumnInfo(name = "fasting") var fasting: Int,
    @ColumnInfo(name = "availableAt") var availableAt: Int,
    @ColumnInfo(name = "popular") var popular: String? = null,
    @ColumnInfo(name = "category") var category: String? = null,
    @ColumnInfo(name = "objectID") var objectID: String? = null
)