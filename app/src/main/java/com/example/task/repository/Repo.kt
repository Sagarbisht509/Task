package com.example.task.repository

import java.time.LocalDate

interface Repo {

    suspend fun getDateOfThisWeek(selectedDate: LocalDate) : ArrayList<Pair<String, String>>

    suspend fun getCurrentSaturdayDate(selectedDate: LocalDate) : LocalDate
}