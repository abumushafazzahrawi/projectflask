package com.example.projectflask.users

import com.google.gson.annotations.SerializedName

data class ResponseUsers(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)


