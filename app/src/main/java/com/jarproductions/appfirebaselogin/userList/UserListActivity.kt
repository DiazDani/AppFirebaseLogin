package com.jarproductions.appfirebaselogin.userList

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.jarproductions.appfirebaselogin.R
import com.jarproductions.appfirebaselogin.databinding.ActivityUserListBinding
import com.jarproductions.appfirebaselogin.login.AuthActivity
import com.jarproductions.appfirebaselogin.userThings.User
import com.jarproductions.appfirebaselogin.userThings.UserAdapter


class UserListActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val userList = mutableListOf<User>()
    private lateinit var binding: ActivityUserListBinding
    private  val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRecyclerView = findViewById(R.id.recycler)
        userAdapter = UserAdapter(userList,this)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userAdapter

        fetchUsersFromFirestore()
        setup()
    }

    private fun fetchUsersFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("clients").get().addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("name") ?: ""
                    val email = document.getString("email") ?: ""
                    val age = document.getString("age")?: ""
                    val user = User(name, email,age)
                    userList.add(user)
                }
                userAdapter.notifyDataSetChanged()
        }
    }
    private fun setup(){
        binding.logoutButton.setOnClickListener{
            viewModel.logout()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}