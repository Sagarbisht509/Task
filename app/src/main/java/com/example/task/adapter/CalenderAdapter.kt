package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.DayDateItemBinding

class CalenderAdapter(
    private var week: ArrayList<Pair<String, String>>
) : RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>() {


    var onItemClick : ((Pair<String, String>) -> Unit)? = null

    class CalenderViewHolder(private val binding: DayDateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Pair<String, String>) {
            binding.dayTextView.text = current.first
            binding.dateTextView.text = current.second
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalenderViewHolder {
        val binding = DayDateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        val current = week[position]
        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(current)
        }
    }

    override fun getItemCount(): Int = week.size
}