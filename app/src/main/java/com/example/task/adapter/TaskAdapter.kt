package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.databinding.TaskItemLayoutBinding
import com.example.task.models.TaskModel

class TaskAdapter(
    private val taskList: List<TaskModel>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var onTaskClick: ((TaskModel) -> Unit)? = null

    class TaskViewHolder(private val binding: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskModel) {
            binding.taskTitle.text = task.title
            val time = "${task.startTime} to ${task.endTime}"
            binding.taskTiming.text = time
            Glide.with(binding.root).load(task.image).into(binding.taskTagImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            TaskItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)

        holder.itemView.setOnClickListener {
            onTaskClick?.invoke(task)
        }
    }
}