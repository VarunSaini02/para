package com.ait.para.data

data class GPT3Result (
    val id: String,
    val gpt3object: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>
)

data class Choice (
    val text: String,
    val index: Long,
    val logprobs: Any? = null,
    val finishReason: String
)

