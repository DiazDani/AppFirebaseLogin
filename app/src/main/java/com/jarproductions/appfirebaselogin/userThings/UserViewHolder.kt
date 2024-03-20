package com.jarproductions.appfirebaselogin.userThings

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jarproductions.appfirebaselogin.databinding.UserItemBinding

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val nameTextView: TextView = binding.nameTextView
    private val emailTextView: TextView = binding.emailTextView
    private val ageTextView: TextView= binding.ageTextView
    fun bind(user: User) {
        nameTextView.text = user.name
        emailTextView.text = user.email
        ageTextView.text=user.age
    }
}