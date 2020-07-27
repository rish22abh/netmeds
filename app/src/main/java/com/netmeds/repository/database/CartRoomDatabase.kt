package com.netmeds.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.netmeds.model.ListModel

@Database(entities = [ListModel::class], version = 1)
abstract class CartRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): CartDao?

    companion object {
        @Volatile
        private var INSTANCE: CartRoomDatabase? = null

        fun getDatabase(context: Context): CartRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CartRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            CartRoomDatabase::class.java, "cart_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}