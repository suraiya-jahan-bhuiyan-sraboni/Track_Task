package com.example.tracktask.view

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracktask.R
import com.example.tracktask.db.Task
import com.example.tracktask.adapter.TaskAdapter
import com.example.tracktask.db.TaskDatabase
import com.example.tracktask.repo.TaskRepo
import com.example.tracktask.viewModel.TaskViewModel
import com.example.tracktask.viewModel.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity() {
    private val newTaskActivityRequestCode = 1
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task:RecyclerView=findViewById(R.id.taskView)
        task.setBackgroundColor(Color.GREEN)
        task.layoutManager=LinearLayoutManager(this)
        task.adapter= TaskAdapter()
        task.setHasFixedSize(true)

        taskViewModel.allTasks.observe(this) { Task ->
            // Update the cached copy of the words in the adapter.
            Task.let {
                TaskAdapter().submitList(it)
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.add_floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewTaskActivity.EXTRA_REPLY)?.let { reply ->
                val task = Task(0,reply)
                taskViewModel.insert(task)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "empty_not_saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

class TaskApplication : Application() {
    val applicationScope= CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepo(database.taskDao()) }
}