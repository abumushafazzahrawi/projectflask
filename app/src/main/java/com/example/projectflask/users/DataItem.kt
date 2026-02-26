package com.example.projectflask.users

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("_id")
    val _id: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("umur")
    val umur: Int,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("jurusan")
    val jurusan: String,

    @field:SerializedName("id")
    val id: Int
)