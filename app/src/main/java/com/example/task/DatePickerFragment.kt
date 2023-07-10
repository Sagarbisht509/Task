package com.example.task

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment(
    val listener : (day: Int, month: Int, year: Int) -> Unit
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, _year: Int, _month: Int, _dayOfMonth: Int) {
        listener(_dayOfMonth, _month, _year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calender = Calendar.getInstance()
        val dayOfMonth = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        return DatePickerDialog(requireContext(),this, year, month, dayOfMonth)
    }
}