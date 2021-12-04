package com.example.firebasedatabase_augbatc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasedatabase_augbatch.R
import com.example.firebasedatabase_augbatch.Student
import com.example.webapi.StudentList.StudentAdapter
import com.example.webapi.StudentList.StudentClickListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity(), StudentClickListener {
    var studentadapter= StudentAdapter(this)
    var stdList = ArrayList<Student>()
    var databaseRefrence: DatabaseReference? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        databaseRefrence= FirebaseDatabase.getInstance("https://androidbatchaug-db-default-rtdb.firebaseio.com/").reference

        getStudentList()

    }

fun getStudentList(){
    val studentList = databaseRefrence?.child("Student")?.orderByChild("Email")
    studentList?.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (postSnapshot in snapshot.children) {
                val student: Student? = postSnapshot.getValue(Student::class.java)
                stdList?.add(student!!)

            }
            studentListRecyclerView.apply{
                layoutManager=
                        LinearLayoutManager(this@StudentListActivity, LinearLayoutManager.HORIZONTAL,false)
                adapter=studentadapter
            }
            studentadapter.studentList(stdList)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    )
}


    override fun studentDetail(student: Student) {
        TODO("Not yet implemented")
    }

}