package com.example.mystudy.data

import com.example.mystudy.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insert(title: String, description: String?, id: Long? = null, isCompleted: Boolean, type: Int, startDate: String, endDate: String?, priority: Int, isCanceled: Boolean)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean, endDate: String)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<Todo>>

    suspend fun getById(id: Long): Todo?


}