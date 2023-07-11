package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.TaskItemLayoutBinding
import com.example.task.models.Task

class TaskAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    inner class TaskViewHolder(private val binding: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, listener: OnItemClickListener) {
            binding.taskTitle.text = task.title
            val time = "${task.startTime} to ${task.endTime}"
            binding.taskTiming.text = time
            Glide.with(binding.root).load(getImageResource(task.category))
                .into(binding.taskTagImage)

            binding.root.setOnClickListener {
                listener.onTaskItemClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            TaskItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnItemClickListener {
        fun onTaskItemClick(task: Task)
    }

    private fun getImageResource(title: String): Int {
        return when (title.lowercase()) {
            "meeting" -> R.drawable.ic_meeting
            "dev" -> R.drawable.ic_dev
            "mobile" -> R.drawable.ic_mobile
            "ui design" -> R.drawable.ic_graphic_ui
            "html" -> R.drawable.ic_html
            "graphic design" -> R.drawable.ic_graphic_ui
            "android app" -> R.drawable.ic_android_ios
            "megento" -> R.drawable.ic_graphic_ui
            "ios app" -> R.drawable.ic_android_ios
            else -> R.drawable.ic_meeting
        }
    }

}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
