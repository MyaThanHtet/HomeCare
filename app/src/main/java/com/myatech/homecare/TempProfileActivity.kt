package com.myatech.homecare

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myatech.homecare.databinding.TempProfileLayoutBinding
import com.myatech.homecare.model.User
import com.myatech.homecare.ui.signup.SignUpViewModel

class TempProfileActivity : AppCompatActivity() {
    private lateinit var tempProfileBinding: TempProfileLayoutBinding
    private lateinit var viewModal: SignUpViewModel
    private lateinit var userData: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempProfileBinding = TempProfileLayoutBinding.inflate(layoutInflater)
        setContentView(tempProfileBinding.root)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[SignUpViewModel::class.java]

        viewModal.getSignedInUser.observe(this) { user ->
            user?.let {
                Log.i("SignUpData", "PatientObserver:${it.name} :: ${it.account_type}")
                displayUserData(it)
                userData = it
            }
        }
        tempProfileBinding.signoutBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Sign Out ::${userData.name}", Toast.LENGTH_SHORT)
                .show()
            viewModal.deleteUser(userData)
        }
    }

    fun displayUserData(user: User) {
        tempProfileBinding.namTv.text = user.name
        tempProfileBinding.addressTv.text = user.address
        tempProfileBinding.phoneTv.text = user.phone
        tempProfileBinding.userTypeTv.text = user.account_type
    }
}