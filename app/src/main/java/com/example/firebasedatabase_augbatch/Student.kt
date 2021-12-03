package com.example.firebasedatabase_augbatch
import com.google.gson.annotations.SerializedName
data class Student(
        val ID:String="",
        val firstName:String="",
        val lastName:String="",
        val marks: Double=0.0,
        val email:String="",
        val phoneNO:String="",
        val fee: Double=0.0
        )