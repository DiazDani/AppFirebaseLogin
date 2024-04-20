package com.jarproductions.appfirebaselogin.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jarproductions.appfirebaselogin.changePassword.ChangePasswordActivity
import com.jarproductions.appfirebaselogin.home.HomeViewModel
import com.jarproductions.appfirebaselogin.login.AuthActivity
import com.jarproductions.appfirebaselogin.userList.UserListActivity

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
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(context, "El registre no ha funcionat!", Toast.LENGTH_SHORT).show()
                    } else {
                        val userDocument = db.collection("clients").document(email)

                        val userData = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "age" to " "
                        )

                        userDocument.set(userData)
                    }
                }
        }



        fun login(email: String, password: String, context: Context) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(context, UserListActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Error d'autenticació. Verifiqueu les vostres credencials", Toast.LENGTH_SHORT).show()
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
        fun checkAccount4Password (email: String,context: Context){
            val currentUser= FirebaseAuth.getInstance().currentUser
            if (currentUser!=null && currentUser.email==email){
                val intent = Intent(context, ChangePasswordActivity::class.java)
                context.startActivity(intent)
            }else{
                Toast.makeText(context, "No es el compte!", Toast.LENGTH_SHORT).show()
            }
        }
        fun deleteAccount(email: String, context: Context) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null && currentUser.email == email) {
                currentUser.delete()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            db.collection("clients").document(email).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "La compte s'ha eliminat correctament!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(context, AuthActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    context.startActivity(intent)
                                }
                                .addOnFailureListener { e ->
                                    Log.w("Repository", "Error al eliminar la cuenta del Firestore", e)
                                    Toast.makeText(context, "Error al eliminar la compte del Firestore", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Log.w("Repository", "Error al eliminar la cuenta de l'autenticació", task.exception)
                            Toast.makeText(context, "Error al eliminar la compte de l'autenticació", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Aquest no és el teu compte!", Toast.LENGTH_SHORT).show()
            }
        }

        fun changePassword(oldPassword: String, newPassword: String, confirmPassword: String, context: Context) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val currentUserEmail = currentUser?.email

            if (currentUserEmail != null && currentUserEmail == FirebaseAuth.getInstance().currentUser?.email) {
                val credential = EmailAuthProvider.getCredential(currentUserEmail, oldPassword)
                currentUser.reauthenticate(credential)
                    .addOnCompleteListener { reAuthTask ->
                        if (reAuthTask.isSuccessful) {
                            if (newPassword == confirmPassword) {
                                currentUser.updatePassword(newPassword)
                                    .addOnCompleteListener { updatePasswordTask ->
                                        if (updatePasswordTask.isSuccessful) {
                                            Toast.makeText(context, "La contrasenya s'ha canviat correctament", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "No s'ha pogut canviar la contrasenya. Si us plau, intenta-ho de nou.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(context, "Les noves contrasenyes no coincideixen.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "La contrasenya antiga no és vàlida.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }




    }
}
