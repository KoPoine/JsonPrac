package com.nepplus.dailyreport_retrofit.api

import com.neppplus.myapplication123.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH

interface APIList {

//    BASE_URL에 해당하는 서버에서, 어떤 기능들을 사용할건지 명시

    //    user
    @GET("/main/banner")
    fun getBanner(): Call<BasicResponse>

}