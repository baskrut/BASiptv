package com.andrewbas.basiptv.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Channel::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun channelsDAO(): ChannelsDAO

    companion object{
        @Volatile var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase?{
            if(INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "appDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}