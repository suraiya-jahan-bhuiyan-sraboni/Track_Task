package com.example.tracktask.view

import android.app.Activity
import android.app.Application
import androidx.activity.viewModels
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }
    private val newTaskLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                data?.getStringExtra(NewTaskActivity.EXTRA_REPLY)?.let { reply ->
                    val task = Task(0, reply)
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task:RecyclerView=findViewById(R.id.taskView)
        task.setBackgroundColor(Color.GREEN)
        task.adapter= TaskAdapter()
        task.layoutManager=LinearLayoutManager(this)
        task.setHasFixedSize(true)
        taskViewModel.allTasks.observe(this) { tasks ->
            // Update the cached copy of the tasks in the adapter.
            (task.adapter as TaskAdapter).submitList(tasks)
        }


        val fab = findViewById<FloatingActionButton>(R.id.add_floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            newTaskLauncher.launch(intent)
        }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        if (requestCode == newTaskActivityRequestCode && resultCode == RESULT_OK) {
//            intentData?.getStringExtra(NewTaskActivity.EXTRA_REPLY)?.let { reply ->
//                val task = Task(0,reply)
//                taskViewModel.insert(task)
//            }
//        } else {
//            Toast.makeText(
//                applicationContext,
//                "empty_not_saved",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
}

class TaskApplication : Application() {
    private val applicationScope= CoroutineScope(SupervisorJob())
    private val database by lazy { TaskDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TaskRepo(database.taskDao()) }
}