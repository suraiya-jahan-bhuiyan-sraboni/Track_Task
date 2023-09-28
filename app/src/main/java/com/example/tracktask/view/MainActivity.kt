package com.example.tracktask.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import androidx.activity.viewModels
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
    private var updatedTaskPosition: Int = -1
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }

    private val newTaskLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                updatedTaskPosition= data?.getIntExtra(NewTaskActivity.EXTRA_TASK_ID,-1)!!
                data.getStringExtra(NewTaskActivity.EXTRA_REPLY)!!.let { reply ->
                    if ((updatedTaskPosition) != -1) {
                        // Update the task in the adapter and the database
                        val updatedTask = Task(updatedTaskPosition, reply)
                        taskViewModel.update(updatedTask)
                        Toast.makeText(
                            applicationContext,
                            "updated",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        // This is a new task creation
                        val task = Task(0, reply)
                        taskViewModel.insert(task)
                        Toast.makeText(
                            applicationContext,
                            "Saved",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
                    Toast.makeText(
                        applicationContext,
                        "Closed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task: RecyclerView = findViewById(R.id.taskView)
        task.setBackgroundColor(Color.GREEN)
        task.adapter = TaskAdapter(clickListener = { selectedItem ->
            listItemClicked(selectedItem)
        },deleteListener= {selectItem ->
            deleteListItemClicked(selectItem)
        }
        )
        task.layoutManager = LinearLayoutManager(this)
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
        val fab1 = findViewById<FloatingActionButton>(R.id.clear_floatingActionButton)
        fab1.setOnClickListener {
            val taskAdapter = task.adapter as TaskAdapter
            val taskCount = taskAdapter.itemCount

            if (taskCount == 0) {
                Toast.makeText(
                    applicationContext,
                    "No Task Available",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Delete All Tasks")
                    .setMessage("Are you sure you want to delete all tasks?")
                    .setPositiveButton("Yes") { _, _ ->
                        taskViewModel.deleteAllTasks()
                        Toast.makeText(
                            applicationContext,
                            "All tasks deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }
    }

    private fun deleteListItemClicked(selectItem: Task) {
        taskViewModel.delete(selectItem)
        Toast.makeText(
            applicationContext,
            "Deleted",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun listItemClicked(task: Task) {
        // Create an intent to open NewTaskActivity with the selected task data
        val intent = Intent(this@MainActivity, NewTaskActivity::class.java).apply {
            putExtra(NewTaskActivity.EXTRA_TASK_ID, task.id)
            putExtra(NewTaskActivity.EXTRA_TASK_TEXT, task.task)
        }
        newTaskLauncher.launch(intent)

    }
}

class TaskApplication : Application() {
    private val applicationScope= CoroutineScope(SupervisorJob())
    private val database by lazy { TaskDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TaskRepo(database.taskDao()) }
}