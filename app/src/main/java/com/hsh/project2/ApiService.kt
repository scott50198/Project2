package com.hsh.project2

import com.hsh.project2.model.User
import com.hsh.project2.model.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("/users/{id}")
    fun getUserDetail(
        @Path("id")id:String): Call<UserDetail>
}