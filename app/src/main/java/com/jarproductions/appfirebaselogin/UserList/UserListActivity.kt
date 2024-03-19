package com.jarproductions.appfirebaselogin.UserList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.jarproductions.appfirebaselogin.R
import com.jarproductions.appfirebaselogin.userThings.User
import com.jarproductions.appfirebaselogin.userThings.UserAdapter


class UserListActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userRecyclerView = findViewById(R.id.recycler)
        userAdapter = UserAdapter(userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userAdapter

        fetchUsersFromFirestore()
    }

    private fun fetchUsersFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("clients").get().addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("name") ?: ""
                    val email = document.getString("email") ?: ""
                    val user = User(name, email)
                    userList.add(user)
                }
                userAdapter.notifyDataSetChanged()
        }
    }
}