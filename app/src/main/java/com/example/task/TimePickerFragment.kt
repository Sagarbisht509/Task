package com.example.task

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment(
    val listener: (String) -> Unit
) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)

        return TimePickerDialog(requireContext(), this, hour, minute, false)
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        val amOrPm: String
        var updatedHour = hourOfDay
        if (hourOfDay == 12) {
            amOrPm = if (minute == 0) "AM" else "PM"
        } else if (hourOfDay > 12) {
            amOrPm = "PM"
            updatedHour = hourOfDay % 12
        } else {
            amOrPm = "AM"
            updatedHour = if (hourOfDay == 0) 12 else updatedHour
        }

        if (minute == 0) {
            listener("$updatedHour $amOrPm")
        } else {
            listener("$updatedHour : $minute $amOrPm")
        }
    }
}