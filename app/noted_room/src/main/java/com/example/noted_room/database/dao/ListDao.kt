package com.example.noted_room.database.dao

import androidx.room.*
import com.example.noted_room.database.model.ListData
import com.example.noted_room.database.model.ListWithListItems


@Dao
interface ListDao {

    @Query("SELECT * FROM ListData")
    fun getAll() : List<ListData>

    @Transaction
    @Query("SELECT * FROM ListData")
    fun getAllListWithListItem() : List<ListWithListItems>

    /**
     * no ":" is added to the LIKE bacause the value passed is static and not from the function
     */
    @Transaction
    @Query("SELECT * FROM ListData WHERE title LIKE '%Favorite%'")
    fun getAllFavorite() : List<ListWithListItems>

    /**
     * ":" is added to the LIKE to tell the database that the query data will be passed from the function
     */
    @Transaction
    @Query("SELECT * FROM ListData WHERE title LIKE :query")
    fun searchQuery(query: String) : List<ListWithListItems>

    @Insert
    fun insert(list: ListData)

    @Insert
    fun insertAll (list : List<ListData>)

    @Delete
    fun delete(list: ListData)
}