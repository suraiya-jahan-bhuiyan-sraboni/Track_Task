package com.example.tracktask.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.tracktask.databinding.ActivityNewTaskBinding

lateinit var binding: ActivityNewTaskBinding
class NewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonsave.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.editword.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val task = binding.editword.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, task)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}