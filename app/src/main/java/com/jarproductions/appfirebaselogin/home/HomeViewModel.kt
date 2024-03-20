package com.jarproductions.appfirebaselogin.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class HomeViewModel: ViewModel() {
    fun updateUserInfo(name: String, email: String,age:String,id:String) {
        Repository.updateUserInfo(name, email,age,id)
    }
    fun deleteUser(email: String){
        Repository.deleteAccount(email)
    }
}