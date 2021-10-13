package com.example.noted_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.noted_room.database.AppDatabase
import com.example.noted_room.database.model.ListItems
import com.example.noted_room.migration.MIGRATION_1_2
import com.example.noted_room.migration.MIGRATION_2_3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"note-database")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .allowMainThreadQueries().build()
        var listDao = db.listDao()
        var listItemDao = db.listItemDao()

        var listdo = listDao.getAll()
        val listItemdo = listItemDao.getAll()

        var show = listDao.getAllListWithListItem()

//        listDao.insert(com.example.noted_room.database.model.ListData(uid = listdo.last().uid + 1,"God"))
//        listItemDao.insert(ListItems( uid = listItemdo.last().uid + 1, 4, "worship"))
//        listItemDao.insert(ListItems(100, 2, "praise"))

      //  Log.i("MainActivity", listdo.toString())
        /**
         * This code was run once to make order_number value to all the already filled itemlist data
         */
//        listItemdo.forEachIndexed {index , listItem ->
//            listItem.order_number = index
//            listItemDao.insert(listItem)
//        }

        // listDao.insert(com.example.noted_room.database.model.ListData(uid = listdo.last().uid + 1,"Faith"))

        /**
         * This code is to search for a particular title content , pay attention to how it use to send parameter into the Dao
         */
        var searchValue = db.listDao().searchQuery("%".plus("faith").plus("%"))

        Log.i("MainActivity", searchValue.toString())



    }
}