package com.jarproductions.appfirebaselogin.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.databinding.ActivityRegisterBinding
import com.jarproductions.appfirebaselogin.login.AuthActivity

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        title = "autenticación"
        binding.button2.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.isNotEmpty()
                && binding.editTextTextPassword.text.isNotEmpty()
                && binding.editTextText.text.isNotEmpty()
            ) {
                viewModel.newUser(

                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString(),binding.editTextText.text.toString(),this
                )
            }


        }
    }
}