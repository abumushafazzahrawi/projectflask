package com.example.projectflask.retrofit

import com.example.projectflask.response_auth.LoginRequest
import com.example.projectflask.response_auth.RegisterRequest
import com.example.projectflask.response_auth.ResponseLogin
import com.example.projectflask.response_auth.ResponseRegister
import retrofit2.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(
        @Body request: LoginRequest
        ): Call<ResponseLogin>

    @POST("register")
    fun registerUser(
        @Body request: RegisterRequest
        ): Call<ResponseRegister>
}