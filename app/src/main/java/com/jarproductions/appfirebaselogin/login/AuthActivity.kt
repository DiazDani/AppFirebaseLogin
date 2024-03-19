package com.jarproductions.appfirebaselogin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.userList.UserListActivity
import com.jarproductions.appfirebaselogin.databinding.ActivityAuthBinding
import com.jarproductions.appfirebaselogin.register.RegisterActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

    }

    private fun setup() {
        title = "autenticación"

        binding.button.setOnClickListener{
            if (binding.editTextTextEmailAddress.text.isNotEmpty()
                && binding.editTextTextPassword.text.isNotEmpty()
            ){
                viewModel.logUser(binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString(),this)
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}