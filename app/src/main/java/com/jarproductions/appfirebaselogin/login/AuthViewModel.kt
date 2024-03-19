package com.jarproductions.appfirebaselogin.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class AuthViewModel : ViewModel() {
    fun logUser(email:String,password:String,context:Context){
        Repository.login(email,password, context )
    }
}