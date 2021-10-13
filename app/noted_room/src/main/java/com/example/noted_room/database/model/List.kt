package com.example.noted_room.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ListData(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "title")
    val title: String,
//    @ColumnInfo(name = "element")
//    val element: MutableList<ListItem>
)

data class ListItem(
    val name : String
)