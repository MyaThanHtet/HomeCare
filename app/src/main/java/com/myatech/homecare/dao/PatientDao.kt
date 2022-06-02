package com.myatech.homecare.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myatech.homecare.model.Patient

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient:Patient)

    @Query("SELECT * FROM patientTbl ")
    fun getPatientById(): LiveData<Patient>

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