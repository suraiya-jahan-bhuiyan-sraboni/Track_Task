package com.example.tracktask.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.tracktask.databinding.ActivityNewTaskBinding

class NewTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewTaskBinding
    private var text=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val taskId = intent.getIntExtra(EXTRA_TASK_ID, -1)
        val taskText = intent.getStringExtra(EXTRA_TASK_TEXT)

        if (taskId != -1 && !taskText.isNullOrBlank()) {
            text=true
            // This is an edit operation, so populate the EditText with task text
            binding.editword.setText(taskText)
            binding.buttonsave.text="Update"
        }
        binding.buttonsave.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.editword.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            }else if(text){
                val task = binding.editword.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, task)
                replyIntent.putExtra(EXTRA_TASK_ID,taskId)
                setResult(RESULT_OK, replyIntent)
            }
            else {
                val task = binding.editword.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, task)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "REPLY"
        const val EXTRA_TASK_ID = "TASK_ID"
        const val EXTRA_TASK_TEXT = "TASK_TEXT"
    }
}