package com.jarproductions.appfirebaselogin.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.changePassword.ChangePasswordActivity
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
        val userAge=intent.getStringExtra("user_age")

        binding.editTextTextEmailAddress2.text = userEmail
        binding.editTextText2.setText(userName)
        binding.editTextText3.setText(userAge)

        setContentView(binding.root)
        setup(userEmail.toString(),this)
    }

    private fun setup(email:String,context: Context){
        binding.button.setOnClickListener{
            val intent = Intent(context, UserListActivity::class.java)
            startActivity(intent)
        }
        binding.buttonUpdate.setOnClickListener{
            viewModel.updateUserInfo(binding.editTextText2.text.toString(),binding.editTextTextEmailAddress2.text.toString(),binding.editTextText3.text.toString(),email,context)

        }
        binding.buttonDelete.setOnClickListener{
            viewModel.deleteUser(email,context)
        }

        binding.changePassword.setOnClickListener {

            viewModel.checkMail(email,context)
        }

    }
}