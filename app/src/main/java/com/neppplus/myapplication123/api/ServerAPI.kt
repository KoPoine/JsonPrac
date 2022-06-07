package com.nepplus.dailyreport_retrofit.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ServerAPI {

    //    Retrofit 클래스 객체화 함수
//    생성자를 쓰지 않고 굳이 객체화를 하는 이유는 어차피 API 통신은
//    => 단일 객체만 만들어서, 모든 화면에서 공유
//    여러개의 객체를 만들 필요 X => SingleTon 패턴
    companion object {

//        서버통신 담당 클래스 : 레트로핏 클래스 객체를 담을 변수
//        아직 안만들었다면? 새로 만들고, 만들어둔게 있다면? 있는 retrofit 재활용

        private var retrofit : Retrofit? = null
        private val BASE_URL = "https://api.gudoc.in/"

        fun getRetrofit(context : Context): Retrofit {

//            Retrofit 라이브러리는, 클래스 차원에서 BASE_URL을 설정할 수 있게 도와줌
//            Retrofit 라이브러리 + Gson 두개의 라이브러리 결함하면 => JSON 파싱이 쉬워짐

            val interceptor = Interceptor {
                with(it) {
                    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwicGFzc3dvcmQiOiI1OTg2YWNjZDJiY2IzOTM0OWUyYTk3OGYxMDMwYzg3ZCJ9.Iu0eIhZSx9qVBxw6YY-oszShhLgMAzBwyPEJPlel7Bw7wfITfqhwVOZoHcXeQBASFlwyldxhfU86XPtQV4xIVw"

                    val newRequest = request().newBuilder()
                        .addHeader("X-Http-Token", token)
                        .build()
                    proceed(newRequest)
                }
            }

            val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // 어느 서버를 기반으로 움직일건지
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // gson 라이브러리와 결합
                    .build()
            }

            return retrofit!!

        }

    }

}