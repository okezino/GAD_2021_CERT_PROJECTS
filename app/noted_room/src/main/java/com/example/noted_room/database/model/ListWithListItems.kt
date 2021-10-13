package com.example.noted_room.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithListItems(
    @Embedded
    val list : ListData,
    @Relation(
        parentColumn = "uid",
        entityColumn = "uid"
    )
    val listItem : List<ListItems>
)
