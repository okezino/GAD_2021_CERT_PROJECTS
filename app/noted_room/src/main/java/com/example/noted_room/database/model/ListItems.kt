package com.example.noted_room.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ListItems(
    @PrimaryKey
    val uid: Int,
    val listId: Int,
    @ColumnInfo(name = "value")
    val value: String,
    var order_number: Int?
)
