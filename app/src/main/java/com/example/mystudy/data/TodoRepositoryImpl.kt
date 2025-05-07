package com.example.mystudy.data

import com.example.mystudy.domain.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun insert(title: String, description: String?, id: Long?, isCompleted: Boolean, type: Int, startDate: String, endDate: String?, priority: Int, isCanceled: Boolean) {
        withContext(Dispatchers.IO) {
            val entity = id?.let {
                dao.getById(it)?.copy(
                    title = title,
                    description = description
                )
            } ?: TodoEntity(
                title = title,
                description = description,
                isCompleted = false,
                type = type,
                startDate = startDate,
                endDate = endDate,
                priority = priority,
                isCanceled = isCanceled
            )
            dao.insert(entity)
        }
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean, endDate: String) {
        withContext(Dispatchers.IO) {
            val existingEntity = dao.getById(id) ?: return@withContext
            val updatedEntity = existingEntity.copy(isCompleted = isCompleted, endDate = endDate)
            if (isCompleted == true) {
                dao.insert(updatedEntity)
            }
        }
    }

    override suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            val existingEntity = dao.getById(id) ?: return@withContext
            dao.delete(existingEntity)
        }
    }


    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    type = entity.type,
                    startDate = entity.startDate,
                    endDate = entity.endDate,
                    priority = entity.priority,
                    isCanceled = entity.isCanceled
                )
            }
        }
    }

    override suspend fun getById(id: Long): Todo? {
        return withContext(Dispatchers.IO) {
            dao.getById(id)?.let { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    type = entity.type,
                    startDate = entity.startDate,
                    endDate = entity.endDate,
                    priority = entity.priority,
                    isCanceled = entity.isCanceled
                )
            }
        }
    }
}