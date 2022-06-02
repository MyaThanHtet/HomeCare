package com.myatech.homecare.network

import com.myatech.homecare.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitClient {

    /*    @FormUrlEncoded
        @POST("/api/homecare/patientregister.php")
        fun registerPatient(
            @Field("profile_url") profile_url:String,
            @Field("name") patient_name: String,
            @Field("address") address: String,
            @Field("phone") phone: String,
            @Field("password") password: String,
            @Field("user_type") user_ype: String,
        ): Call<String>*/

    @FormUrlEncoded
    @POST("/api/homecare/nurseregister.php")
    fun registerUser(
        @Field("profile_url") profile_url: String,
        @Field("name") nurse_name: String,
        @Field("address") nurse_address: String,
        @Field("phone") nurse_phone: String,
        @Field("password") password: String,
        @Field("user_type") user_ype: String,
        @Field("ratting") ratting: Int,
        @Field("point") point: Int,
    ): Call<String>
/*        @FormUrlEncoded
        @POST("/api/updatenote.php")
        fun updateNote(
            @Field("id") id: Int,
            @Field("name") name: String,
            @Field("amount") amount: Int,
            @Field("date") date: String,
            @Field("isPaid") isPaid: Int,
            @Field("description") description: String
        ): Call<String>

        @FormUrlEncoded
        @POST("/api/deletenote.php")
        fun deleteNote(
            @Field("id") id: Int
        ): Call<String>

        @GET("/api/note.php")
        fun getAllNotes(
        ): Call<MutableList<Note>>

        @GET("/api/totalamount.php")
        fun getTotalAmount(): Call<String>*/

    @FormUrlEncoded
    @POST("/api/homecare/nursesignin.php")
    fun signIn(
        @Field("phone") phone: String,
        @Field("password") password: String,
    ): Call<MutableList<User>>
}