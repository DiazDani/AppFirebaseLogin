package com.jarproductions.appfirebaselogin.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jarproductions.appfirebaselogin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private  val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setup()
    }

    private fun setup(){
        binding.button.setOnClickListener{
            viewModel.logout()
        }
    }
}