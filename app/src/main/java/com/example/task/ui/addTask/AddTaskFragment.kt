package com.example.task.ui.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.DatePickerFragment
import com.example.task.TimePickerFragment
import com.example.task.databinding.FragmentAddTaskBinding
import com.example.task.local_db.TaskDatabase
import com.example.task.models.Task
import com.example.task.repository.TaskRepository
import com.google.android.material.chip.Chip
import com.google.gson.Gson

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: AddTaskViewModel

    private lateinit var taskDatabase: TaskDatabase
    private lateinit var taskRepository: TaskRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskDatabase = TaskDatabase.getDatabase(requireContext())
        taskRepository = TaskRepository(taskDatabase)
        val factory = AddTaskViewModelProviderFactory(taskRepository)
        viewModel = ViewModelProvider(this, factory)[AddTaskViewModel::class.java]

        setInitialValues()

        binding.titleEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onTitleTextChanged(text.toString())
        }

        binding.descriptionEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onDescriptionTextChanged(text.toString())
        }

        binding.dateEditText.setOnClickListener {
            onSelectDateEditTextClicked()
        }

        binding.startTimeEditText.setOnClickListener {
            showTimePickerDialog("start")
        }

        binding.endTimeEditText.setOnClickListener {
            showTimePickerDialog("end")
        }

        binding.createNewTaskBtn.setOnClickListener {
            if (viewModel.validateUserInput()) {
                Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
                viewModel.addNewTask(getTask())
                navigateToHomeScreen()
            } else {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        getChosenChip()
    }

    private fun setInitialValues() {
        val json = arguments?.getString("task")
        if(json != null) {
            val task = Gson().fromJson(json, Task::class.java)
            task?.let {
                viewModel.onTitleTextChanged(it.title)
                viewModel.onDescriptionTextChanged(it.description)
                viewModel.onStartTimeChanged(it.startTime)
                viewModel.onEndTimeChanged(it.endTime)
                viewModel.onCategoryChange(it.category)

                binding.titleEditText.setText(it.title)
                binding.descriptionEditText.setText(it.description)
                binding.dateEditText.setText(it.date)
                binding.startTimeEditText.setText(it.startTime)
                binding.endTimeEditText.setText(it.endTime)
            }
        }


    }

    private fun getTask(): Task = Task(
        title = viewModel.title.value,
        description = viewModel.description.value,
        date = viewModel.date.value,
        startTime = viewModel.startTime.value,
        endTime = viewModel.endTime.value,
        category = viewModel.category.value
    )

    private fun showTimePickerDialog(from: String) {
        val timePicker = TimePickerFragment { time -> onTimeSelected(time, from) }
        timePicker.show(childFragmentManager, "Time Picker")
    }

    private fun onTimeSelected(time: String, from: String) {
        if (from == "start") {
            viewModel.onStartTimeChanged(time)
            binding.startTimeEditText.setText(time)
        } else {
            viewModel.onEndTimeChanged(time)
            binding.endTimeEditText.setText(time)
        }
    }

    private fun onSelectDateEditTextClicked() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "Date Picker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val sDay = if (day < 10) "0$day" else day.toString()
        val mMonth = month + 1
        val sMonth = if (mMonth < 10) "0$mMonth" else mMonth.toString()
        val sYear = year.toString()
        val dateString = "$sYear-$sMonth-$sDay"
        // val parseDate = LocalDate.parse(dateString)
        viewModel.onDateChanged(dateString)
        binding.dateEditText.setText(dateString)
    }

    private fun getChosenChip() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedIds ->
            val chip: Chip? = group.findViewById(checkedIds)

            chip?.let {
                viewModel.onCategoryChange(chip.text.toString())
            } ?: kotlin.run {

            }
        }
    }

    private fun navigateToHomeScreen() {
        findNavController().popBackStack()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}