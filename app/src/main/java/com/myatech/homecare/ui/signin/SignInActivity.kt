package com.myatech.homecare.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myatech.homecare.MainActivity
import com.myatech.homecare.TempProfileActivity
import com.myatech.homecare.databinding.ActivitySigninBinding
import com.myatech.homecare.model.User
import com.myatech.homecare.network.RetrofitClient
import com.myatech.homecare.network.ServiceBuilder
import com.myatech.homecare.ui.signup.SignUpViewModel
import com.myatech.homecare.ui.signup.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySigninBinding
    private lateinit var viewModal: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SignUpViewModel::class.java]


        Log.i(
            SignInActivity::class.simpleName,
            "${mBinding.signInPhoneEdt.text}::&&&::${mBinding.signInPasswordEdt.text}"
        )

        //  mBinding.signInBtn.isClickable = !(phone.isEmpty() && password.isEmpty())


        mBinding.signInBtn.setOnClickListener {
            signInUser(
                mBinding.signInPhoneEdt.text.toString(),
                mBinding.signInPasswordEdt.text.toString()
            )
            Toast.makeText(applicationContext, "Success Sign in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInUser(phone: String, password: String) {
        val retrofit = ServiceBuilder.buildService2(RetrofitClient::class.java)
        retrofit.signIn(
            phone, password
        ).enqueue(
            object : Callback<MutableList<User>> {
                override fun onResponse(
                    call: Call<MutableList<User>>,
                    response: Response<MutableList<User>>
                ) {
                    val usersResponse = response.body()
                    Log.i(SignInActivity::class.simpleName, usersResponse.toString())
                    if (usersResponse.toString() != "[]") {
                        usersResponse?.let {
                            viewModal.addUser(it[0])
                            startActivity(
                                Intent(
                                    applicationContext,
                                    MainActivity::class.java
                                )
                            )
                        }
                    } else {
                        Toast.makeText(applicationContext, "Sign Fail", Toast.LENGTH_SHORT).show()
                    }


                }

                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    Log.i(SignInActivity::class.simpleName, "$t")
                }

            }
        )
        Log.i(SignInActivity::class.simpleName, "$phone::::$password")

    }

}