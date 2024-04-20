package com.jarproductions.appfirebaselogin.changePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.R
import com.jarproductions.appfirebaselogin.databinding.ActivityChangePasswordBinding
import com.jarproductions.appfirebaselogin.databinding.ActivityHomeBinding
import com.jarproductions.appfirebaselogin.home.HomeViewModel

class ChangePasswordActivity : AppCompatActivity() {
    private  val viewModel: ChangePasswordViewModel by viewModels()
    private lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setup()
    }

    private fun setup(){
        binding.button.setOnClickListener {
            onBackPressed()
        }
        binding.buttonUpdate.setOnClickListener {
            val oldPassword = binding.editTextTextPassword2.text.toString()
            val newPassword = binding.editTextTextPassword3.text.toString()
            val confirmPassword = binding.editTextTextPassword4.text.toString()

            viewModel.changePassword(oldPassword, newPassword, confirmPassword, this)
        }
    }
}