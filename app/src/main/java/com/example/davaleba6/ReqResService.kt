package com.example.davaleba6

import com.zura.mysuperapp.api.dto.ReqResData
import com.zura.mysuperapp.api.dto.Resource
import com.zura.mysuperapp.api.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("users")
    fun getUsers(@Query("page")page: Int): Call<ReqResData<List<User>>>
    @GET("users/{userId}")
    fun getUser(@Path("userId") id: Int): Call<ReqResData<User>>
    @GET("unknown")
    fun getResources(): Call<ReqResData<List<Resource>>>
}