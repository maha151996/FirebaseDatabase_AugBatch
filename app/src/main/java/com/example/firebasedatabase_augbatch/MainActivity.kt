package com.example.firebasedatabase_augbatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var databaseRefrence: DatabaseReference? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseRefrence=FirebaseDatabase.getInstance("https://androidbatchaug-db-default-rtdb.firebaseio.com/").reference
        submit.setOnClickListener {
//            saveStudent()
            studentList()
        }
    }

    private fun studentList() {
        val studentList = databaseRefrence?.child("Student")?.orderByChild("Email")
        studentList?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    // TODO: handle the post
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        )
    }

    private fun saveStudent() {
        val studentID= databaseRefrence?.child("Student")?.push()?.key ?: return
var student=Student("Theta","Solutions",34.0,"thetasolutions@gmail.com","030987654",45.0 )
       databaseRefrence!!.child("Administration").child(studentID).setValue(student).addOnCompleteListener {
           if(it.isSuccessful)
           {
               Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
           }
           else{
               Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

           }
       }
    }
}