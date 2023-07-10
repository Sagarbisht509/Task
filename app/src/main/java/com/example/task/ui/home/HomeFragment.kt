package com.example.task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.DatePickerFragment
import com.example.task.R
import com.example.task.adapter.CalenderAdapter
import com.example.task.adapter.TaskAdapter
import com.example.task.databinding.FragmentHomeBinding
import com.example.task.local_db.TaskDatabase
import com.example.task.models.Task
import com.example.task.models.TaskModel
import com.example.task.repository.RepoImpl
import com.example.task.repository.TaskRepository
import com.google.gson.Gson
import java.time.LocalDate

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var repo: RepoImpl
    private lateinit var taskRepository: TaskRepository
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var viewModel: HomeViewModel
    private lateinit var calenderAdapter: CalenderAdapter
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskDatabase = TaskDatabase.getDatabase(requireContext())
        repo = RepoImpl()
        taskRepository = TaskRepository(taskDatabase)
        val factory = HomeViewModelProviderFactory(repo, taskRepository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        observer()

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment)
        }

        binding.calenderIcon.setOnClickListener {
            onCalenderIconCLicked()
        }
    }

    private fun onCalenderIconCLicked() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "Date Picker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val sDay = if (day < 10) "0$day" else day.toString()
        val mMonth = month + 1
        val sMonth = if (mMonth < 10) "0$mMonth" else mMonth.toString()
        val sYear = year.toString()
        val dateString = "$sYear-$sMonth-$sDay"
        val parseDate = LocalDate.parse(dateString)
        viewModel.updateDate(parseDate)
        setMonthYearOfDate(parseDate)
    }

    private fun setMonthYearOfDate(date: LocalDate) {
        val text = date.month.toString() + ", " + date.year.toString()
        binding.monthYearTextView.text = text
    }

    private fun observer() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            setUpDayDateRecyclerView(it)
        })

        viewModel.localDate.observe(viewLifecycleOwner, Observer { date ->
            setMonthYearOfDate(date)
        })

        viewModel.taskList.observe(viewLifecycleOwner, Observer {
            setUpTaskRecyclerView(it)
        })
    }


    private fun setUpTaskRecyclerView(tasks: List<TaskModel>) {
        taskAdapter = TaskAdapter(tasks)
        binding.taskRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = taskAdapter
        }

        taskAdapter.onTaskClick = {
            val bundle = Bundle()
            bundle.putString("task", Gson().toJson(it))
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment, bundle)
        }
    }

    private fun setUpDayDateRecyclerView(week: ArrayList<Pair<String, String>>) {

        calenderAdapter = CalenderAdapter(week)
        binding.dayDateRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            adapter = calenderAdapter
        }

        calenderAdapter.onItemClick = {
            Toast.makeText(context, it.first + " " + it.second, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}