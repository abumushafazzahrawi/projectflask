package com.example.projectflask.users

import com.google.gson.annotations.SerializedName

data class ResponseDeleteUsers(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)
