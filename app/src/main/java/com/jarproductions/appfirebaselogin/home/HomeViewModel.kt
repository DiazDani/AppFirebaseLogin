package com.jarproductions.appfirebaselogin.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class HomeViewModel: ViewModel() {
    fun checkMail(mail:String, context: Context){
        Repository.checkAccount4Password(mail,context)
    }
    fun updateUserInfo(name: String, email: String,age:String,id:String) {
        Repository.updateUserInfo(name, email,age,id)
    }
    fun deleteUser(email: String, context: Context){
        Repository.deleteAccount(email,context)
    }
}