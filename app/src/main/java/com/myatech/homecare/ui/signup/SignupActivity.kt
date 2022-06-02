package com.myatech.homecare.ui.signup

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.myatech.homecare.databinding.ActivitySignupBinding
import com.myatech.homecare.model.User
import com.myatech.homecare.network.RetrofitClient
import com.myatech.homecare.network.ServiceBuilder
import com.myatech.homecare.ui.signin.SignInActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupBinding
    private lateinit var userTypeSwitch: SwitchCompat
    private lateinit var viewModal: SignUpViewModel
    private var userType: String = "Patient"

    private val defaultButtonTintColor = "#848484"
    private val onFormValidButtonTintColor = "#D90077"
    private var errorMessage: String? = null
    private val _name = MutableStateFlow("")
    private val _address = MutableStateFlow("")
    private val _phone = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    private val formIsValid = combine(
        _name,
        _address,
        _phone,
        _password,
    ) { _name, _address, _phone, _password ->


        val nameIsValid = _name.isNotEmpty()
        val addressIsValid = _address.isNotEmpty()
        val phoneIsValid = _phone.isNotEmpty()
        val passwordIsValid = _password.isNotEmpty()

        errorMessage = when {
            nameIsValid.not() -> "fill name"
            addressIsValid.not() -> "fill address"
            phoneIsValid.not() -> "fill phone"
            passwordIsValid.not() -> "fill password"

            else -> null
        }

        errorMessage?.let {
            // Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        nameIsValid and addressIsValid and phoneIsValid and passwordIsValid
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)


        //form validation
        with(signupBinding) {
            signUpNameTv.doOnTextChanged { text, _, _, _ ->
                _name.value = text.toString()
            }
            signUpAddressTv.doOnTextChanged { text, _, _, _ ->
                _address.value = text.toString()
            }
            signUpPhoneTv.doOnTextChanged { text, _, _, _ ->
                _phone.value = text.toString()
            }
            signUpPasswordTv.doOnTextChanged { text, _, _, _ ->
                _password.value = text.toString()
            }

        }

        signupBinding.signInTv.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        /*  setup switch button for select userType*/
        userTypeSwitch = signupBinding.switchUserType
        customizedSwitchBtn()

        lifecycleScope.launch {

            formIsValid.collect {
                signupBinding.signUpBtn.apply {
                    backgroundTintList = ColorStateList.valueOf(
                        Color.parseColor(
                            if (it) {

                                onFormValidButtonTintColor
                            } else {
                                defaultButtonTintColor
                            }
                        )

                    )

                    isClickable = it


                }
            }
        }

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SignUpViewModel::class.java]

        /* viewModal.getNurse.observe(this, Observer { list ->
            list?.let {
                Log.i("SignUpData", "NurseObserver:$it.name :: ${it.userType}")
            }
        })

    viewModal.getPatient.observe(this, Observer { list ->
        list?.let {
            Log.i("SignUpData", "PatientObserver:$it.name :: ${it.userType}")
        }
    })*/




        signupBinding.signUpBtn.setOnClickListener {

            //prepare data for register
            val user = User(
                id = null,
                profile_url = "https://",
                name = _name.value,
                address = _address.value,
                phone = _phone.value,
                password = _password.value,
                account_type = userType,
                ratting = 0,
                point = 0
            )

            registerUser(user)

        }

    }


    private fun customizedSwitchBtn() {
        userTypeSwitch.setOnCheckedChangeListener { _, _ ->
            if (userTypeSwitch.isChecked) {
                signupBinding.switchTipNurseTv.visibility = View.GONE
                signupBinding.switchTipPatientTv.visibility = View.VISIBLE
                userType = "Nurse"
                Toast.makeText(applicationContext, "You are $userType", Toast.LENGTH_SHORT).show()
            } else {
                signupBinding.switchTipPatientTv.visibility = View.GONE
                signupBinding.switchTipNurseTv.visibility = View.VISIBLE
                userType = "Patient"
                Toast.makeText(applicationContext, "You are $userType", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun registerUser(
        user: User

    ) {
        val retrofit = ServiceBuilder.buildService(RetrofitClient::class.java)
        retrofit.registerUser(
            user.profile_url,
            user.name,
            user.address,
            user.phone,
            user.password,
            user.account_type,
            user.ratting,
            user.point
        )
            .enqueue(
                object : Callback<String> {

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.i(SignupActivity::class.simpleName, "on FAILURE!!!!$t")
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        val jsonResponse: String = response.body().toString()
                        Log.i(SignupActivity::class.simpleName, jsonResponse)
                        val jsonObject = JSONObject(jsonResponse)
                        if (jsonObject.optString("status") == "true") {
                            viewModal.addUser(user)
                        } else {
                            Toast.makeText(applicationContext, "Register Fail", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            )

    }
}