package com.jarproductions.appfirebaselogin.register

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class RegisterViewModel:  ViewModel() {
    fun newUser(email:String,password:String,nom:String,context: Context){
        Repository.addAlum(email,password,nom,context)

    }
}