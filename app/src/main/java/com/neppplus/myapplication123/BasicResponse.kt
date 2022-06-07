package com.neppplus.myapplication123

data class BasicResponse(
    val code : Int,
    val message : String,
    val data : DataResponse
) {
}