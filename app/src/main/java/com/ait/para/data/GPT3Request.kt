package com.ait.para.data

import com.google.gson.annotations.SerializedName

data class GPT3Request(
    @SerializedName("prompt") var prompt: String? = null,
    @SerializedName("max_tokens") var maxTokens: Int? = null,
    @SerializedName("temperature") var temperature: Float? = null,
    @SerializedName("top_p") var topP: Float? = null,
    @SerializedName("n") var n: Int? = null,
    @SerializedName("stream") var stream: Boolean? = null,
    @SerializedName("logprobs") var logprobs: Int? = null,
    @SerializedName("stop") var stop: String? = null,
    @SerializedName("echo") var echo: Boolean? = null
)

