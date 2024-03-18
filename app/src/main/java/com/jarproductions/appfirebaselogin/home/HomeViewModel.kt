package com.jarproductions.appfirebaselogin.home

import androidx.lifecycle.ViewModel
import com.jarproductions.appfirebaselogin.Repository

class HomeViewModel: ViewModel() {
     fun logout() {
        Repository.logout()
    }
}