package com.example.webapi.StudentList

import com.example.firebasedatabase_augbatch.Student


interface StudentClickListener {
    fun studentDetail(student: Student)
}