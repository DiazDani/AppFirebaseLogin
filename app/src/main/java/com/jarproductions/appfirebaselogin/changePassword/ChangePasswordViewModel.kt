package com.jarproductions.appfirebaselogin.changePassword

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class ChangePasswordViewModel : ViewModel() {
    fun changePassword(oldPassword: String, newPassword: String, confirmPassword: String, context: Context) {
        Repository.changePassword(oldPassword, newPassword, confirmPassword, context)
    }

}