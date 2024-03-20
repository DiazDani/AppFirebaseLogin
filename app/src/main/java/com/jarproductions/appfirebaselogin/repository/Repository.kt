package com.jarproductions.appfirebaselogin.repository

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class Repository {
    companion object {

        val db = Firebase.firestore

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

                        val userDocument = db.collection("clients").document(email)

                        val userData = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "age" to 0
                        )

                        // Establece los datos del usuario en el documento con el ID como el correo electrónico
                        userDocument.set(userData)

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
        fun updateUserInfo(name: String, email: String,age: String,id:String){

            val userData = mapOf(
                "name" to name,
                "email" to email,
                "age" to age
            )
            Log.i("jose", "${name},${email}" )

            db.collection("clients").document(id).update(userData).addOnSuccessListener{
                Log.i("jose", "se ha insertado correctamente")
            }


        }
        fun deleteAccount(email: String) {
           db.collection("clients").document(email).delete()
        }

    }
}
