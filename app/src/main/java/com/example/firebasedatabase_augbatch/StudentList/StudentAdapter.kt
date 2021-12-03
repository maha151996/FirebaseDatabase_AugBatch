package com.example.webapi.StudentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedatabase_augbatch.R
import com.example.firebasedatabase_augbatch.Student
import kotlinx.android.synthetic.main.student_item_view.view.*

class StudentAdapter(var clickListnere:StudentClickListener): RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {

    var globalStudentList:List<Student> = arrayListOf()
    class studentViewHolder(studentitemview: View) : RecyclerView.ViewHolder(studentitemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): studentViewHolder {
//        val view=
//            LayoutInflater.from(parent.context).inflate(R.layout.student_item_view,parent,false)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item_view,parent,false)
        return studentViewHolder(view)
    }

    override fun onBindViewHolder(holder: studentViewHolder, position: Int) {
        val student =globalStudentList[position]
        holder.itemView.studentName.text=student.firstName.toString()
        holder.itemView.email.text=student.email.toString()
        //holder.itemView.phoneNumber.text=student.toString()
        holder.itemView.marks.text=student.marks.toString()
        holder.itemView.setOnClickListener{
clickListnere.studentDetail(student)
        }
    }

    override fun getItemCount(): Int {
        return globalStudentList.size
    }

    fun studentList(studentList: List<Student>) {
        globalStudentList= studentList
    }
}