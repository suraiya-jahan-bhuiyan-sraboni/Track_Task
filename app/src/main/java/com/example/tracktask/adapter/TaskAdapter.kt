package com.example.tracktask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tracktask.R
import com.example.tracktask.db.Task



class TaskAdapter(private val clickListener: (Task) -> Unit):ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current =getItem(position)
        holder.bind(current.task) { isChecked ->
            if (isChecked) {
                // Checkbox is checked, show a toast message
                Toast.makeText(holder.itemView.context, "Done", Toast.LENGTH_SHORT).show()



            }
        }
        holder.itemView.setOnClickListener{

            clickListener(current)
        }
    }



    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val taskItemView:CheckBox=itemView.findViewById(R.id.checkBox)

        fun bind(text:String?, clickListener: (Boolean) -> Unit){
            taskItemView.text=text
            taskItemView.setOnCheckedChangeListener { _, isChecked ->
                // Invoke the clickListener with the checkbox state
                clickListener(isChecked)

            }
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