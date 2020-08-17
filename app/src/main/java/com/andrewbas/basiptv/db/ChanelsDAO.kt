package com.andrewbas.basiptv.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

interface ChannelsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun incertChanel(chanel: Channel): Completable/*Single<Chanel>*/

    @Update
    fun updateChanel(chanel: Channel): Completable/*Comparable<Chanel>*/

    @Delete
    fun deleteChanel(chanel: Channel): Completable

    @Query("SELECT * FROM Channel WHERE title == :title")
    fun getChannelByTitle(title: String): Flowable<Channel>

    @Query("SELECT * FROM Channel WHERE chNumber == :chNumber")
    fun getChannelByNumber(chNumber: Int):Flowable<Channel>

    @Query("SELECT * FROM Channel WHERE type == :type")
    fun getChannelByType(type: String): Flowable<Channel>

    @Query("SELECT * FROM Channel ")
    fun getAllChannels():Flowable<Channel>
}