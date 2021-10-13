package com.example.noted_room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noted_room.database.dao.ListDao
import com.example.noted_room.database.dao.ListItemDao
import com.example.noted_room.database.model.ListData
import com.example.noted_room.database.model.ListItems

@Database(entities = [ListData::class, ListItems::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao() : ListDao
    abstract fun listItemDao() : ListItemDao
}