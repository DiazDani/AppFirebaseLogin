package com.jarproductions.appfirebaselogin.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class HomeViewModel: ViewModel() {
    fun updateUserInfo(name: String, email: String,id:String) {
        Repository.updateUserInfo(name, email,id)
    }
    fun deleteUser(email: String, context: Context){
        Repository.deleteAccount(email,context)
    }
}