package com.guilherme.recyclerview.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 2)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        private lateinit var INSTANCE: MovieDataBase

        fun getDataBase(context: Context): MovieDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, "movieDB")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}