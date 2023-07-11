package com.example.task.repository

import java.time.LocalDate

interface Repo {

    suspend fun getDateOfThisWeek(selectedDate: LocalDate) : ArrayList<LocalDate>

    suspend fun getCurrentSaturdayDate(selectedDate: LocalDate) : LocalDate
}