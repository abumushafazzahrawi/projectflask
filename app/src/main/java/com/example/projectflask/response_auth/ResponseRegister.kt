package com.example.projectflask.response_auth

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)
