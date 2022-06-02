package com.myatech.homecare.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patientTbl")
data class Patient(
    @PrimaryKey
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "user_type")
    var userType: String,

    @ColumnInfo(name = "profile_url")
    var profileUrl: String,

    @ColumnInfo(name = "password")
    var password: String
)