package com.example.noted_room.database.dao

import androidx.room.*
import com.example.noted_room.database.model.ListData
import com.example.noted_room.database.model.ListItems
import com.example.noted_room.database.model.ListWithListItems

@Dao
interface ListItemDao {

    @Query("SELECT * FROM ListItems")
    fun getAll() : List<ListItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: ListItems)

    @Insert
    fun insertAll (list : List<ListItems>)

    @Delete
    fun delete(list: ListItems)
}