package com.jarproductions.appfirebaselogin.register

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.Repository

class RegisterViewModel:  ViewModel() {
    fun newUser(email:String,password:String,context: Context){
        Repository.addAlum(email,password,context)

    }
}