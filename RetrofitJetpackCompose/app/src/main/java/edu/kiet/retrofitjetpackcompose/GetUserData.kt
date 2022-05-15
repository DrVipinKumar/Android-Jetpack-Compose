package edu.kiet.retrofitjetpackcompose

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val baseUrl="https://api.github.com/"
interface GetUserData {
    @GET("users")
    fun getUserInfo(): Call<UserData>
}

object UserDataInstance {
      val getUserData:GetUserData
      init {
             val retrofit =Retrofit.Builder()
                 .baseUrl(baseUrl)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
          getUserData=retrofit.create(GetUserData::class.java)
      }
}