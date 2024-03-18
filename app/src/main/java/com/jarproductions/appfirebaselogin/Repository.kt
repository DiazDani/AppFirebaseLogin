package com.jarproductions.appfirebaselogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.jarproductions.appfirebaselogin.login.AuthActivity

class Repository {
    companion object{

        fun addAlum(email: String, password: String, context: Context){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if (!it.isSuccessful){
                    showAlert(context)
                }
            }
        }
        fun login(email: String,password: String, context: Context){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if (!it.isSuccessful){
                    showAlert(context)
                }
            }
        }
        fun logout(){
            FirebaseAuth.getInstance().signOut()
        }

        fun showAlert(context:Context){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage("Error detectat! revisa que tot estigui ben possat")
           builder.setPositiveButton("Aceptar", null)
            val dialog:AlertDialog=builder.create()
            dialog.show()
        }
    }

}