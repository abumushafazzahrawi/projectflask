package com.example.projectflask.users

import com.google.gson.annotations.SerializedName

data class ResponseCreateUsers(

	@field:SerializedName("data")
	val data: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)
