package com.example.tracktask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tracktask.R
import com.example.tracktask.db.Task

class TaskAdapter:ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current =getItem(position)
        holder.bind(current.task)
    }
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val taskItemView:CheckBox=itemView.findViewById(R.id.checkBox)

        fun bind(text:String?){
            taskItemView.text=text
        }
        companion object{
            fun create(parent: ViewGroup): TaskViewHolder {
                val view:View=LayoutInflater.from(parent.context).inflate(R.layout.task_list,parent,false)
                return TaskViewHolder(view)
            }
        }

    }

    class TaskComparator:DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.task==newItem.task
        }

    }
}