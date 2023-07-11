package com.example.task.repository

import java.time.DayOfWeek
import java.time.LocalDate

class RepoImpl : Repo {

    override suspend fun getDateOfThisWeek(selectedDate: LocalDate): ArrayList<LocalDate> {

        val week: ArrayList<LocalDate> = ArrayList()

        var current = getCurrentSaturdayDate(selectedDate)
        val endDate = current.plusWeeks(1)

        while (current.isBefore(endDate)) {
            week.add(current)
            current = current.plusDays(1)
        }

        return week
    }

    override suspend fun getCurrentSaturdayDate(selectedDate: LocalDate): LocalDate {
        var currentDate = selectedDate
        val oneWeekAgo = currentDate.minusWeeks(1)
        while (currentDate.isAfter(oneWeekAgo)) {
            if (currentDate.dayOfWeek == DayOfWeek.SUNDAY)
                break

            currentDate = currentDate.minusDays(1)
        }

        return currentDate
    }
}