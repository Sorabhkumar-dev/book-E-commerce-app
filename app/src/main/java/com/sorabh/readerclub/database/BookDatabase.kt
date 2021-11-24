package com.sorabh.readerclub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sorabh.readerclub.retrofit.BookDescription

@Database(entities = [BookDescription::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao

    companion object {
        private var INSTANCE: BookDatabase? = null
        private const val BookDBName = "BOOK_DESCRIPTION_DB"
        @Synchronized
        fun getBookDatabaseInstance(context: Context):BookDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    BookDatabase::class.java,
                    BookDBName
                ).build()
            }
                return INSTANCE!!

        }
    }

}