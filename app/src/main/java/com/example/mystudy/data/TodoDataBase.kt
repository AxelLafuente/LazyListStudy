package com.example.mystudy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ TodoEntity::class],
    version = 1,
)
abstract  class TodoDataBase : RoomDatabase() {
    abstract val dao: TodoDao
}

object TodoDatabaseProvider {

    @Volatile
    private var INSTANCE: TodoDataBase? = null

    fun provide(context: Context): TodoDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TodoDataBase::class.java,
                "todo-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}