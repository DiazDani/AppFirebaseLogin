package com.jarproductions.appfirebaselogin.userThings

import android.content.Intent
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarproductions.appfirebaselogin.databinding.UserItemBinding
import com.jarproductions.appfirebaselogin.home.HomeActivity

class UserAdapter(private val userList: List<User>, private val context: Context) : RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra("user_name", user.name)
            intent.putExtra("user_email", user.email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}