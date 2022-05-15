package edu.kiet.recyclelazycolumn.retrofit

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

val baseUrl:String="https://api.github.com/"
interface GetUserInfo {
    @GET("users")
    fun getUserData(): Call<UserData>
}
object UserDataInstance {
    val getUserInfo:GetUserInfo
    init {
                val retrofit =Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        getUserInfo=retrofit.create(GetUserInfo::class.java)
    }
}