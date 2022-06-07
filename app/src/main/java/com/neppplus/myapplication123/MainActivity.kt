package com.neppplus.myapplication123

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.nepplus.dailyreport_retrofit.api.APIList
import com.nepplus.dailyreport_retrofit.api.ServerAPI
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = "banner"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = ServerAPI.getRetrofit(this)
        val APIList = retrofit.create(APIList::class.java)

        APIList.getBanner().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                Log.d(TAG, response.toString())

                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    val br = response.body()!!
                    val firstImg = JSONObject(br.data.banners[0].toString())
                    val firstUrl = firstImg.getString("img_url")
                    Log.d(TAG, firstImg.toString())
                    Log.d(TAG, firstUrl.toString())
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })

        Glide.with(this)
    }
}