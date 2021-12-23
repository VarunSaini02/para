package com.ait.para

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ait.para.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    companion object {
        const val KEY_GPT3_TYPE = "KEY_GPT3_TYPE"
        const val KEY_QUESTION = "KEY_QUESTION"
        const val KEY_SARCASM = "KEY_SARCASM"
        const val KEY_CHILD = "KEY_CHILD"
        const val KEY_INSTRUCT = "KEY_INSTRUCT"
    }

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQuestion.setOnClickListener {
            nextActivity(KEY_QUESTION)
        }
        binding.btnSarcasm.setOnClickListener {
            nextActivity(KEY_SARCASM)
        }
        binding.btnChild.setOnClickListener {
            nextActivity(KEY_CHILD)
        }
        binding.btnInstruct.setOnClickListener {
            nextActivity(KEY_INSTRUCT)
        }
    }

    private fun nextActivity(key: String) {
        val intent = Intent(this@HomeScreenActivity, ScrollingActivity::class.java)
        intent.putExtra(KEY_GPT3_TYPE, key)
        startActivity(intent)
    }
}