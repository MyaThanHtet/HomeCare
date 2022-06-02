package com.myatech.homecare.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myatech.homecare.dao.UserDao
import com.myatech.homecare.model.Patient
import com.myatech.homecare.model.User

@Database(entities = [Patient::class, User::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "roomDb"
                )
                    .build()
            }

            return INSTANCE as MyDatabase
        }
    }
}