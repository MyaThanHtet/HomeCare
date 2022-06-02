package com.myatech.homecare.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myatech.homecare.model.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(note: User)

    @Query("SELECT * FROM userTbl ")
    fun getSignedInUser(): LiveData<User>

    @Delete
    fun delete(user: User)

/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    *//* @Query("DELETE FROM noteTbl WHERE id =:id")
     fun delete(id:Int)*//*
    @Delete
    fun delete(note: Note)

    @Update
    fun update(note:Note)
    @Query("SELECT SUM(amount) FROM noteTbl WHERE ispaid=:isPaid")
    fun totalAmount(isPaid:Boolean=true):Int*/
}