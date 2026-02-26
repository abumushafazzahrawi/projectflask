package com.example.projectflask.response_auth

import com.google.gson.annotations.SerializedName

data class ResponseProfile(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class Data(

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("exp")
	val exp: Int,

	@field:SerializedName("username")
	val username: String
)
