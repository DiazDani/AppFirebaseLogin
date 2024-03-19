package com.jarproductions.appfirebaselogin.userList

import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.repository.Repository

class UserListViewModel : ViewModel() {
    fun logout() {
        Repository.logout()
    }
}