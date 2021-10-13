package com.example.noted_room.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `ListItems` (`uid` INTEGER NOT NULL, `listId` INTEGER NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`uid`))")
    }

}
val MIGRATION_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `ListItems`ADD COLUMN order_number INTEGER")
    }

}