package com.jarproductions.appfirebaselogin.repository

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    companion object {
        val db = FirebaseFirestore.getInstance()
        private fun showAlert(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage("Error detectat! revisa que tot estigui ben possat")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        fun addAlum(email: String, password: String, name: String, context: Context) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        showAlert(context)
                    } else {

                        val userData = hashMapOf(
                            "name" to name,
                            "email" to email
                        )
                        db.collection("clients").add(userData)

                    }
                }
        }


        fun login(email: String, password: String, context: Context) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        showAlert(context)
                    }
                }
        }

        fun logout() {
            FirebaseAuth.getInstance().signOut()
        }

    }
}
