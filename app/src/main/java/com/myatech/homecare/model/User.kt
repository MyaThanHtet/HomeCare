package com.myatech.homecare.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTbl")
data class User(
    @PrimaryKey
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "account_type")
    var account_type: String,

    @ColumnInfo(name = "profile_url")
    var profile_url: String,

    @ColumnInfo(name = "point")
    var point: Int,

    @ColumnInfo(name = "ratting")
    var ratting: Int,

    @ColumnInfo(name = "password")
    var password: String,
)