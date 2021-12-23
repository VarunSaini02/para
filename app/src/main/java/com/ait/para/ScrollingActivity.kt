package com.ait.para

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ait.para.data.GPT3Request
import com.ait.para.data.GPT3Result
import com.ait.para.databinding.ActivityScrollingBinding
import com.ait.para.retrofit.GPT3API
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScrollingActivity : AppCompatActivity() {

    companion object {
        const val baseUrl = "https://api.openai.com"
    }

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var gpt3type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getGPT3Type()

        binding.toolbar.title = when (gpt3type) {
            HomeScreenActivity.KEY_QUESTION -> getString(R.string.questions)
            HomeScreenActivity.KEY_SARCASM -> getString(R.string.sarcasm)
            HomeScreenActivity.KEY_CHILD -> getString(R.string.angry_child)
            HomeScreenActivity.KEY_INSTRUCT -> getString(R.string.instruct)
            else -> {
                getString(R.string.app_name)
            }
        }

        binding.fabSearch.setOnClickListener {
            refreshResponseTV(binding.etInput.text.toString())
        }
        binding.fabClear.setOnClickListener {
            clearRequestAndResponse()
        }
    }

    private fun refreshResponseTV(input: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gpt3Service = retrofit.create(GPT3API::class.java)

        val gpt3Call = gpt3Service.getResponse(getAppropriateGPT3Request(input))

        gpt3Call.enqueue(object : Callback<GPT3Result> {
            override fun onFailure(call: Call<GPT3Result>, t: Throwable) {
                snackbarForFailure()
            }

            override fun onResponse(
                call: Call<GPT3Result>,
                response: Response<GPT3Result>
            ) {
                if (response.body() != null) {
                    var gpt3Result = response.body()!!
                    binding.tvResponse.text = gpt3Result.choices[0].text.trim()
                } else {
                    snackbarForFailure()
                }
            }
        })
    }

    private fun getAppropriateGPT3Request(input: String): GPT3Request {
        val prompt =
            when (gpt3type) {
                HomeScreenActivity.KEY_QUESTION -> getString(R.string.question_prompt, input)
                HomeScreenActivity.KEY_SARCASM -> getString(R.string.sarcasm_prompt, input)
                HomeScreenActivity.KEY_CHILD -> getString(R.string.child_prompt, input)
                HomeScreenActivity.KEY_INSTRUCT -> input
                else -> {
                    throw RuntimeException(getString(R.string.invalid_model_config))
                }
            }
        return GPT3Request(
            prompt = prompt,
            maxTokens = 311,
            echo = (gpt3type == HomeScreenActivity.KEY_INSTRUCT)
        )
    }

    private fun getGPT3Type() {
        val extras = intent.extras
        if (extras != null) {
            gpt3type = extras.getString(HomeScreenActivity.KEY_GPT3_TYPE)!!
        }

        binding.etInput.hint = when (gpt3type) {
            HomeScreenActivity.KEY_QUESTION -> getString(R.string.enter_question)
            HomeScreenActivity.KEY_SARCASM -> getString(R.string.enter_prompt)
            HomeScreenActivity.KEY_CHILD -> getString(R.string.enter_prompt)
            HomeScreenActivity.KEY_INSTRUCT -> getString(R.string.enter_instructions)
            else -> {
                getString(R.string.enter_prompt)
            }
        }
    }

    private fun snackbarForFailure() {
        Snackbar.make(
            binding.root,
            getString(R.string.something_went_wrong),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun clearRequestAndResponse() {
        binding.etInput.text.clear()
        binding.tvResponse.text = getString(R.string.empty)
    }
}