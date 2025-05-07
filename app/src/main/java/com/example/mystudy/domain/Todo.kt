package com.example.mystudy.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean = false,
    val type: Int = 0,
    val startDate: String,
    val endDate: String?,
    val priority: Int = 0,
    val isCanceled: Boolean = false,
)

// falek object
val todo1 = Todo(id = 1, title = "Grocery Shopping", "", isCompleted = false, type = 0, priority = 0, isCanceled = false, startDate = "01/01/2025", endDate = null)
val todo2 = Todo(id = 2, title = "Pay Bills", "", isCompleted = false, type = 0, priority = 0, isCanceled = false, startDate = "01/01/2025", endDate = null)
val todo3 = Todo(id = 3, title = "Book Doctor Appointment", "", isCompleted = false, type = 0, priority = 0, isCanceled = false, startDate = "01/01/2025", endDate = null)