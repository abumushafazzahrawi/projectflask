package com.example.projectflask.response_auth

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("token")
	val token: String,

    @field:SerializedName("username")
    val username: String
)
