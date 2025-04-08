package com.example.mystudy.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean = false
)

// falek object
val todo1 = Todo(id = 1, title = "Grocery Shopping", "", isCompleted = false)
val todo2 = Todo(id = 2, title = "Pay Bills", "", isCompleted = true)
val todo3 = Todo(id = 3, title = "Book Doctor Appointment", "", isCompleted = false)