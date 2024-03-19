package com.jarproductions.appfirebaselogin.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.databinding.ActivityHomeBinding
import com.jarproductions.appfirebaselogin.userList.UserListActivity

class HomeActivity : AppCompatActivity() {
    private  val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val userName = intent.getStringExtra("user_name")
        val userEmail = intent.getStringExtra("user_email")

        binding.editTextTextEmailAddress2.setText(userEmail)
        binding.editTextText2.setText(userName)

        setContentView(binding.root)
        setup(userName.toString(), userEmail.toString())
    }

    private fun setup(name:String,email:String){
        binding.button.setOnClickListener{
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
        binding.buttonUpdate.setOnClickListener{
            viewModel.updateUserInfo(name,email)
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}