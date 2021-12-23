package com.ait.para.retrofit

import com.ait.para.data.GPT3Request
import com.ait.para.data.GPT3Result
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface GPT3API {
    @Headers("Content-Type: application/json", "Authorization: Bearer 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")
    @POST("v1/engines/davinci-instruct-beta-v3/completions")
    fun getResponse(@Body gpt3request: GPT3Request): Call<GPT3Result>
}
