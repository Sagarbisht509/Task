package com.example.task.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.databinding.DayDateItemBinding
import java.time.LocalDate

/*
class CalenderAdapter(
    private var week: ArrayList<LocalDate>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION // Initial no position is selected

    inner class CalenderViewHolder(private val binding: DayDateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: LocalDate, isSelected: Boolean, listener: OnItemClickListener,) {
            binding.dayTextView.text = date.dayOfWeek.toString().subSequence(0, 2)
            binding.dateTextView.text = date.dayOfMonth.toString()

            binding.root.setOnClickListener {
                listener.onCalenderItemClick(date)
            }

            if (isSelected) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.selected_bg_color
                    )
                )
            } else {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.unselected_bg_color
                    )
                )
            }
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
        holder.bind(current, selectedPosition == position, listener)

        holder.itemView.setOnClickListener {
            val oldPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int = week.size

    interface OnItemClickListener {
        fun onCalenderItemClick(localDate: LocalDate)
    }

}*/









class CalenderAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<LocalDate, CalenderAdapter.CalenderViewHolder>(CalenderDIffCallback()) {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CalenderViewHolder(private val binding: DayDateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: LocalDate, listener: OnItemClickListener) {
            var monthFirstThreeChar = date.dayOfWeek.toString().subSequence(0, 3)
            val formatMonth = monthFirstThreeChar[0] + monthFirstThreeChar.substring(1, 3).lowercase()
            binding.dayTextView.text = formatMonth
            binding.dateTextView.text = date.dayOfMonth.toString()

            binding.root.setOnClickListener {
                listener.onCalenderItemClick(date)
            }
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
        holder.bind(getItem(position), listener)
    }

    interface OnItemClickListener {
        fun onCalenderItemClick(localDate: LocalDate)
    }

}

class CalenderDIffCallback : DiffUtil.ItemCallback<LocalDate>() {
    override fun areItemsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
        return oldItem == newItem
    }

}